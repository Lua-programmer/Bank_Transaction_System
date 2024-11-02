package io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request

import jakarta.validation.constraints.NotBlank

data class AmountRequest(

    @field:NotBlank
    val amount: String,

    @field:NotBlank
    val accountNumber: String
)
