package io.github.luaprogrammer.digital_bank.adapters.input.consumer.mapper

import io.github.luaprogrammer.digital_bank.adapters.output.producer.event.AccountEvent
import io.github.luaprogrammer.digital_bank.adapters.output.producer.event.PersonEvent
import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import io.github.luaprogrammer.digital_bank.usecases.domain.Person

fun AccountEvent.toAccount(): Account =
    this.let {
        Account(
            accountNumber = it.accountNumber,
            balance = it.balance,
            person = it.person.toPerson()
        )
    }

fun PersonEvent.toPerson(): Person =
    this.let {
        Person(
            name = it.name,
            cpf = it.cpf,
            email = it.email
        )
    }
