package io.github.luaprogrammer.digital_bank.usecases

import io.github.luaprogrammer.digital_bank.ports.input.AccountInputPort
import io.github.luaprogrammer.digital_bank.ports.output.AccountOutputPort
import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import io.github.luaprogrammer.digital_bank.usecases.utils.generateAccountNumber
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AccountUseCase(
    private val accountOutputPort: AccountOutputPort
) : AccountInputPort {

    private val logger: Logger = LoggerFactory.getLogger(AccountUseCase::class.java)

    override fun createAccount(account: Account) =
        account.run {
            logger.info("Gerando número de conta...")
            this.accountNumber = generateAccountNumber()
            accountOutputPort.saveAccount(this)
        }

    override fun getAccount(accountNumber: String): Account? =
        accountOutputPort.getAccount(accountNumber)
}
