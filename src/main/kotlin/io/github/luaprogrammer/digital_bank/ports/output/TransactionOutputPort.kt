package io.github.luaprogrammer.digital_bank.ports.output

import io.github.luaprogrammer.digital_bank.usecases.domain.Transaction

interface TransactionOutputPort {

    fun getTransactions(): List<Transaction>

    fun saveTransaction(transaction: Transaction)
}