package io.github.luaprogrammer.digital_bank.ports.output

import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import org.springframework.stereotype.Service

@Service
interface AccountOutputPort {

    fun saveAccount(account: Account)

    fun getAccount(accountNumber: String): Account?

}
