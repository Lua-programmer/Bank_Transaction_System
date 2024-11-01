package io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request

data class PersonRequest(

    val id: Int?,

    val name: String,

    val cpf: String,

    val email: String,
)