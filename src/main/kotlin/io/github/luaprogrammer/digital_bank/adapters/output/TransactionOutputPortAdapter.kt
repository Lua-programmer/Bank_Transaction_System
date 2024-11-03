package io.github.luaprogrammer.digital_bank.adapters.output

import io.github.luaprogrammer.digital_bank.adapters.output.repository.TransactionRepository
import io.github.luaprogrammer.digital_bank.ports.output.TransactionOutputPort
import io.github.luaprogrammer.digital_bank.usecases.domain.Transaction
import org.springframework.stereotype.Service

@Service
class TransactionOutputPortAdapter(
    private val transactionRepository: TransactionRepository
) : TransactionOutputPort {

    override fun getTransactions(): List<Transaction> =
        transactionRepository.findAll().ifEmpty { listOf() }

    override fun saveTransaction(transaction: Transaction) {
        transactionRepository.save(transaction)
    }
}