package io.github.luaprogrammer.digital_bank.adapters.output.repository

import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Int> {

    @Query("SELECT * from account a WHERE a.account_number = ?1", nativeQuery = true)
    fun findByAccountNumber(accountNumber: String): Account?
}