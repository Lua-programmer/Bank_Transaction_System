package io.github.luaprogrammer.digital_bank.adapters.output.repository

import io.github.luaprogrammer.digital_bank.usecases.domain.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Int>