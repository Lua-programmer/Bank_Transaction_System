package io.github.luaprogrammer.digital_bank.usecases

import io.github.luaprogrammer.digital_bank.ports.input.AccountInputPort
import io.github.luaprogrammer.digital_bank.ports.output.AccountOutputPort
import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import io.github.luaprogrammer.digital_bank.usecases.utils.generateAccountNumber
import org.springframework.stereotype.Service

@Service
class AccountUseCase(
    private val accountOutputPort: AccountOutputPort
) : AccountInputPort {

    override fun createAccount(account: Account) =
        account.run {
            this.accountNumber = generateAccountNumber()
            accountOutputPort.createAndSaveAccount(this)
        }

    override fun getAccount(accountNumber: String): Account {
        return accountOutputPort.getAccount(accountNumber)
    }
}