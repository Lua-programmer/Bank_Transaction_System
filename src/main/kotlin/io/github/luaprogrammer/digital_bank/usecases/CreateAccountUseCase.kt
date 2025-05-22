package io.github.luaprogrammer.digital_bank.usecases

import io.github.luaprogrammer.digital_bank.ports.input.CreateAccountInputPort
import io.github.luaprogrammer.digital_bank.ports.output.AccountOutputPort
import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import io.github.luaprogrammer.digital_bank.usecases.utils.generateAccountNumber
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateAccountUseCase(
    private val accountOutputPort: AccountOutputPort
) : CreateAccountInputPort {

    private val logger: Logger = LoggerFactory.getLogger(CreateAccountUseCase::class.java)

    override fun createAccount(account: Account) =
        account.run {
            logger.info("Gerando número de conta...")
            this.accountNumber = generateAccountNumber()
            accountOutputPort.saveAccount(this)
        }
}
