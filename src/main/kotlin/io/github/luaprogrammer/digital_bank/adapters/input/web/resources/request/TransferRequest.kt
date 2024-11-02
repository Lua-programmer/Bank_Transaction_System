package io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request

import jakarta.validation.constraints.NotBlank

data class TransferRequest(

    @field:NotBlank
    val accountOrigin: String,

    @field:NotBlank
    val accountDestination: String,

    @field:NotBlank
    val amount: String
)
