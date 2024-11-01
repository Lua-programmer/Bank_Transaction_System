package io.github.luaprogrammer.digital_bank.adapters.input.web.resources

import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.AmountRequest
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.TransferRequest
import io.github.luaprogrammer.digital_bank.ports.input.TransactionInputPort
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("bank-digital/v1/transaction/")
class TransactionResource(
    private val transactionInputPort: TransactionInputPort
) {

    @PostMapping("deposit")
    fun deposit(
        @RequestBody request: AmountRequest
    ) {
        transactionInputPort.deposit(request.amount, request.accountNumber)
    }

    @PostMapping("withdraw")
    fun withdraw(
        @RequestBody request: AmountRequest
    ) {
        transactionInputPort.withdraw(request.amount, request.accountNumber)
    }

    @PostMapping("transfer")
    fun transfer(
        @RequestBody request: TransferRequest
    ) {
        transactionInputPort.transfer(
            request.amount,
            request.accountOrigin,
            request.accountDestination
        )
    }
}