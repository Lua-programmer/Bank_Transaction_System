package io.github.luaprogrammer.digital_bank.ports.input

import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import org.springframework.stereotype.Service

@Service
interface CreateAccountInputPort {

    fun createAccount(account: Account)
}
