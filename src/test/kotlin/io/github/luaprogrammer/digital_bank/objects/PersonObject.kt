package io.github.luaprogrammer.digital_bank.objects

import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.PersonRequest

object PersonObject {

    fun createPersonRequest(): PersonRequest =
        PersonRequest(
            name = "Luiz Fernando",
            cpf = "123.456.789-00",
            email = "luiz-fernando@gmail.com"
        )
}