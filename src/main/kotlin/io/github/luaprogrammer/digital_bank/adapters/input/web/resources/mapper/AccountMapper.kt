package io.github.luaprogrammer.digital_bank.adapters.input.web.resources.mapper

import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.AccountRequest
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.PersonRequest
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.response.AccountResponse
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.response.PersonResponse
import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import io.github.luaprogrammer.digital_bank.usecases.domain.Person

fun AccountRequest.toAccount() =
    this.let {
        Account(
            id = it.id,
            accountNumber = it.accountNumber,
            balance = it.balance!!,
            person = it.person.toPerson()
        )
    }

fun PersonRequest.toPerson() =
    this.let {
        Person(
            id = it.id,
            name = it.name,
            cpf = it.cpf,
            email = it.email
        )
    }

fun Account.toAccountResponse() =
    this.let {
        AccountResponse(
            accountNumber = it.accountNumber,
            balance = it.balance,
            person = it.person.toPersonResponse()
        )
    }

fun Person.toPersonResponse() =
    this.let {
        PersonResponse(
            name = it.name,
            email = it.email
        )
    }
