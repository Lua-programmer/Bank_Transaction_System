package io.github.luaprogrammer.digital_bank

import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.containers.OracleContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper
import java.time.Duration

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@DirtiesContext
abstract class IntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    companion object {
        @Container
        val oracle: OracleContainer = OracleContainer("gvenzl/oracle-xe:21-slim")
            .withUsername("dev_user_test")
            .withPassword("dev_pass_test")
            .withReuse(true)
            .withStartupTimeout(Duration.ofMinutes(5))
            .apply {
                start()
            }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { oracle.jdbcUrl }
            registry.add("spring.datasource.username") { oracle.username }
            registry.add("spring.datasource.password") { oracle.password }
            registry.add("spring.datasource.driver-class-name") { "oracle.jdbc.OracleDriver" }

            registry.add("flyway.url") { oracle.jdbcUrl }
            registry.add("flyway.user") { oracle.username }
            registry.add("flyway.password") { oracle.password }
        }
    }
}