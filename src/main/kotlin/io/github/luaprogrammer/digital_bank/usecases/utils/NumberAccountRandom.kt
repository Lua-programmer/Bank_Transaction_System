package io.github.luaprogrammer.digital_bank.usecases.utils

import kotlin.random.Random

val generatedNumbers = mutableSetOf<String>()


fun generateAccountNumber(): String {
    while (true) {
        val bank = Random.nextInt(1000, 9999)
        val agency = Random.nextInt(1000, 9999)
        val account = Random.nextInt(100000, 999999)
        val checkDigit = Random.nextInt(0, 10)

        val accountNumber = String.format("%04d-%04d-%06d-%d", bank, agency, account, checkDigit)

        if (generatedNumbers.add(accountNumber)) {
            return accountNumber
        }
    }
}