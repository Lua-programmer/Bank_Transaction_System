package io.github.luaprogrammer.digital_bank.adapters.input.web.resources.mapper

import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.response.TransactionResponse
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.response.TransactionsResponse
import io.github.luaprogrammer.digital_bank.usecases.domain.Transaction

fun List<Transaction>.toTransactionsResponse(): TransactionsResponse =
    TransactionsResponse(
        transactions = this.map { it.toTransactionResponse() }
    )

fun Transaction.toTransactionResponse(): TransactionResponse =
    TransactionResponse(
        type = this.type,
        amount = this.amount,
        origin = this.origin,
        destination = this.destination,
        accountNumber = this.accountNumber
    )