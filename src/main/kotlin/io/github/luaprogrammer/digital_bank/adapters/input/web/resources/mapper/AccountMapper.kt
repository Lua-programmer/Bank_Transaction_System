package io.github.luaprogrammer.digital_bank.adapters.input.web.resources.mapper

import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.AccountRequest
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.PersonRequest
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.response.AccountResponse
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.response.PersonResponse
import io.github.luaprogrammer.digital_bank.adapters.output.producer.event.AccountEvent
import io.github.luaprogrammer.digital_bank.adapters.output.producer.event.PersonEvent
import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import io.github.luaprogrammer.digital_bank.usecases.domain.Person

fun AccountRequest.toAccount() =
    this.let {
        Account(
            accountNumber = it.accountNumber,
            balance = it.balance!!,
            person = it.person.toPerson()
        )
    }

fun AccountRequest.toAccountEvent() =
    this.let {
        AccountEvent(
            accountNumber = it.accountNumber,
            balance = it.balance!!,
            person = it.person.toPersonEvent()
        )
    }

fun PersonRequest.toPerson() =
    this.let {
        Person(
            name = it.name,
            cpf = it.cpf,
            email = it.email
        )
    }

fun PersonRequest.toPersonEvent() =
    this.let {
        PersonEvent(
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
