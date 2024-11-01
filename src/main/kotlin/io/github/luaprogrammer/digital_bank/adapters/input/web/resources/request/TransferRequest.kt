package io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request

data class TransferRequest(

    val accountOrigin: String,

    val accountDestination: String,

    val amount: String
)
