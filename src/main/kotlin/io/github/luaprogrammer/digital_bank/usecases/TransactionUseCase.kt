package io.github.luaprogrammer.digital_bank.usecases

import io.github.luaprogrammer.digital_bank.ports.exceptions.InsufficientBalanceException
import io.github.luaprogrammer.digital_bank.ports.input.TransactionInputPort
import io.github.luaprogrammer.digital_bank.ports.output.AccountOutputPort
import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TransactionUseCase(
    private val accountOutputPort: AccountOutputPort
) : TransactionInputPort {

    @Transactional
    override fun deposit(
        amount: String,
        accountNumber: String
    ) {
        val account = validatedAccount(accountNumber)
        accountOutputPort.saveAccount(
            account!!.copy(
                balance = account.balance.add(amount.toBigDecimal())
            )
        )
    }

    @Transactional
    override fun withdraw(
        amount: String,
        accountNumber: String
    ) {
        val account = validatedAccount(accountNumber)
        if (account?.balance!! < amount.toBigDecimal()) {
            throw InsufficientBalanceException("Saldo Insuficiente")
        }
        accountOutputPort.saveAccount(
            account.copy(
                balance = account.balance.subtract(amount.toBigDecimal())
            )
        )
    }

    override fun transfer(
        amount: String,
        accountOrigin: String,
        accountDestination: String
    ) {
        amount.run {
            withdraw(this, accountOrigin)
            deposit(this, accountDestination)
        }
    }

    private fun validatedAccount(accountNumber: String): Account? =
        accountOutputPort.getAccount(accountNumber)
}
