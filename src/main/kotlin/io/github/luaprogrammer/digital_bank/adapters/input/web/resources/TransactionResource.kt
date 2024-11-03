package io.github.luaprogrammer.digital_bank.adapters.input.web.resources

import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.constants.TRANSACTION_RESOURCE_V1_URI
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.mapper.toTransactionsResponse
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.AmountRequest
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.TransferRequest
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.response.TransactionsResponse
import io.github.luaprogrammer.digital_bank.ports.input.TransactionInputPort
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(TRANSACTION_RESOURCE_V1_URI)
class TransactionResource(
    private val transactionInputPort: TransactionInputPort
) {

    @PostMapping("deposit")
    fun deposit(
        @RequestBody @Valid request: AmountRequest
    ) {
        transactionInputPort.deposit(
            request.amount,
            request.accountNumber
        )
    }

    @PostMapping("withdraw")
    fun withdraw(
        @RequestBody @Valid request: AmountRequest
    ) {
        transactionInputPort.withdraw(
            request.amount,
            request.accountNumber
        )
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

    @GetMapping
    fun transactions(): TransactionsResponse =
        transactionInputPort.transactions().toTransactionsResponse()
}
