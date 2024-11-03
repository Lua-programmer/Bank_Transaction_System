package io.github.luaprogrammer.digital_bank.usecases

import io.github.luaprogrammer.digital_bank.ports.exceptions.InsufficientBalanceException
import io.github.luaprogrammer.digital_bank.ports.input.TransactionInputPort
import io.github.luaprogrammer.digital_bank.ports.output.AccountOutputPort
import io.github.luaprogrammer.digital_bank.ports.output.TransactionOutputPort
import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import io.github.luaprogrammer.digital_bank.usecases.domain.Transaction
import io.github.luaprogrammer.digital_bank.usecases.domain.TransactionType
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TransactionUseCase(
    private val accountOutputPort: AccountOutputPort,
    private val transactionOutputPort: TransactionOutputPort
) : TransactionInputPort {

    @Transactional
    override fun deposit(
        amount: String,
        accountNumber: String
    ) {
        val account = validatedAccount(accountNumber)

        synchronized(account) {
            accountOutputPort.saveAccount(
                account.copy(
                    balance = account.balance.add(amount.toBigDecimal())
                )
            )

            transactionOutputPort.saveTransaction(
                Transaction(
                    type = TransactionType.D,
                    amount = amount.toBigDecimal(),
                    accountNumber = accountNumber
                )
            )
        }
    }

    @Transactional
    override fun withdraw(
        amount: String,
        accountNumber: String
    ) {
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
            transactionOutputPort.saveTransaction(
                Transaction(
                    type = TransactionType.W,
                    amount = amount.toBigDecimal(),
                    accountNumber = accountNumber
                )
            )
        }
    }

    override fun transfer(
        amount: String,
        accountOrigin: String,
        accountDestination: String
    ) {
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
    }

    override fun transactions(): List<Transaction> {
        return transactionOutputPort.getTransactions()
    }

    private fun validatedAccount(accountNumber: String): Account =
        accountOutputPort.getAccount(accountNumber)
}
