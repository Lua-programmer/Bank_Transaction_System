package io.github.luaprogrammer.digital_bank.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper

@Configuration
class JacksonConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }
}