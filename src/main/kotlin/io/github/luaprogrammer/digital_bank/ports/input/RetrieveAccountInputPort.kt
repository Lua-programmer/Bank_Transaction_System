package io.github.luaprogrammer.digital_bank.ports.input

import io.github.luaprogrammer.digital_bank.usecases.domain.Account

interface RetrieveAccountInputPort {

    fun getAccount(accountNumber: String): Account?
}