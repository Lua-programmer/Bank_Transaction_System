package io.github.luaprogrammer.digital_bank.usecases

import io.github.luaprogrammer.digital_bank.ports.input.RetrieveAccountInputPort
import io.github.luaprogrammer.digital_bank.ports.output.AccountOutputPort
import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import org.springframework.stereotype.Service

@Service
class RetrieveAccountUseCase(
    private val accountOutputPort: AccountOutputPort,
): RetrieveAccountInputPort {

    override fun getAccount(accountNumber: String): Account? =
        accountOutputPort.getAccount(accountNumber)
}