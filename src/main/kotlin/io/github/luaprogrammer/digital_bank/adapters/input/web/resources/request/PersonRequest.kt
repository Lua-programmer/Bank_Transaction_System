package io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.br.CPF

data class PersonRequest(

    @field:NotBlank
    val name: String,

    @field:NotBlank
    @CPF(message = "CPF inválido")
    val cpf: String,

    @field:NotBlank
    @Email(message = "E-mail inválido")
    val email: String,
)
