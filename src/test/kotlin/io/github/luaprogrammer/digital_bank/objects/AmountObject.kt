package io.github.luaprogrammer.digital_bank.objects

import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.AmountRequest
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.TransferRequest

object AmountObject {

    fun createDepositRequest(): AmountRequest =
        AmountRequest(
            amount = "800.00",
            accountNumber = "5773-3133-585332-5",
        )

    fun createWithdrawRequest(): AmountRequest =
        AmountRequest(
            amount = "200.00",
            accountNumber = "5773-3133-585332-5",
        )

    fun createTransferRequest(): TransferRequest =
        TransferRequest(
            amount = "50.00",
            accountOrigin = "5773-3133-585332-5",
            accountDestination = "5773-3133-585332-6",
        )
}