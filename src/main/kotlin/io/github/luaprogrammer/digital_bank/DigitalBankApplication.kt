package io.github.luaprogrammer.digital_bank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class DigitalBankApplication

fun main(args: Array<String>) {
    runApplication<DigitalBankApplication>(*args)
}
