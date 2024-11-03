package io.github.luaprogrammer.digital_bank.ports.input

import io.github.luaprogrammer.digital_bank.usecases.domain.Transaction
import org.springframework.stereotype.Service

@Service
interface TransactionInputPort {

    fun deposit(amount: String, accountNumber: String)

    fun withdraw(amount: String, accountNumber: String)

    fun transfer(amount: String, accountOrigin: String, accountDestination: String)

    fun transactions(): List<Transaction>

}
