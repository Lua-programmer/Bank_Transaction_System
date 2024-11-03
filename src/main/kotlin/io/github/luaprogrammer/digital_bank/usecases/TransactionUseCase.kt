package io.github.luaprogrammer.digital_bank.usecases

import io.github.luaprogrammer.digital_bank.ports.exceptions.InsufficientBalanceException
import io.github.luaprogrammer.digital_bank.ports.input.TransactionInputPort
import io.github.luaprogrammer.digital_bank.ports.output.AccountOutputPort
import io.github.luaprogrammer.digital_bank.ports.output.TransactionOutputPort
import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import io.github.luaprogrammer.digital_bank.usecases.domain.Transaction
import io.github.luaprogrammer.digital_bank.usecases.domain.TransactionType
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TransactionUseCase(
    private val accountOutputPort: AccountOutputPort,
    private val transactionOutputPort: TransactionOutputPort
) : TransactionInputPort {

    private val logger: Logger = LoggerFactory.getLogger(TransactionUseCase::class.java)

    @Transactional
    override fun deposit(
        amount: String,
        accountNumber: String
    ) {

        logger.info("Validando conta...")
        val account = validatedAccount(accountNumber)

        synchronized(account) {
            accountOutputPort.saveAccount(
                account.copy(
                    balance = account.balance.add(amount.toBigDecimal())
                )
            )
            logger.info("Saldo atualizado com sucesso...")

            transactionOutputPort.saveTransaction(
                Transaction(
                    type = TransactionType.D,
                    amount = amount.toBigDecimal(),
                    accountNumber = accountNumber
                )
            )

            logger.info("Depósito realizado com sucesso...")
        }
    }

    @Transactional
    override fun withdraw(
        amount: String,
        accountNumber: String
    ) {
        logger.info("Validando conta...")
        val account = validatedAccount(accountNumber)

        synchronized(account) {

            if (account.balance < amount.toBigDecimal()) {
                throw InsufficientBalanceException("Saldo Insuficiente")
            }
            accountOutputPort.saveAccount(
                account.copy(
                    balance = account.balance.subtract(amount.toBigDecimal())
                )
            )
            logger.info("Saldo atualizado com sucesso...")

            transactionOutputPort.saveTransaction(
                Transaction(
                    type = TransactionType.W,
                    amount = amount.toBigDecimal(),
                    accountNumber = accountNumber
                )
            )
            logger.info("Saque realizado com sucesso...")
        }
    }

    override fun transfer(
        amount: String,
        accountOrigin: String,
        accountDestination: String
    ) {

        logger.info("Validando contas...")
        val origin = validatedAccount(accountOrigin)
        val destination = validatedAccount(accountDestination)

        synchronized(origin) {
            synchronized(destination) {
                amount.run {
                    withdraw(this, origin.accountNumber!!)
                    deposit(this, destination.accountNumber!!)
                }
            }
        }

        transactionOutputPort.saveTransaction(
            Transaction(
                type = TransactionType.T,
                amount = amount.toBigDecimal(),
                origin = accountOrigin,
                destination = accountDestination
            )
        )
        logger.info("Transferência realizada com sucesso...")
    }

    override fun transactions(): List<Transaction> {
        return transactionOutputPort.getTransactions()
    }

    private fun validatedAccount(accountNumber: String): Account =
        accountOutputPort.getAccount(accountNumber)
}
