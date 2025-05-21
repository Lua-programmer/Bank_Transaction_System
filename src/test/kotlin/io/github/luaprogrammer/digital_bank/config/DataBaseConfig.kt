package io.github.luaprogrammer.digital_bank.config

import org.junit.jupiter.api.AfterAll
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.OracleContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.Duration

@TestConfiguration
@Testcontainers
class DataBaseConfig {
    companion object {

        @Container
        val oracle: OracleContainer = OracleContainer("gvenzl/oracle-xe:21-slim")
            .withUsername("dev_user_test")
            .withPassword("dev_pass_test")
            .withStartupTimeout(Duration.ofMinutes(5))

        init {
            oracle.start()
            System.setProperty("spring.datasource.url", oracle.jdbcUrl)
            System.setProperty("spring.datasource.username", oracle.username)
            System.setProperty("spring.datasource.password", oracle.password)
            System.setProperty("spring.datasource.driver-class-name", "oracle.jdbc.OracleDriver")

            System.setProperty("flyway.url", oracle.jdbcUrl)
            System.setProperty("flyway.user", oracle.username)
            System.setProperty("flyway.password", oracle.password)
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            oracle.stop()
        }
    }
}