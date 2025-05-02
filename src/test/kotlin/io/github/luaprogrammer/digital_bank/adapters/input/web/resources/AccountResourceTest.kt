package io.github.luaprogrammer.digital_bank.adapters.input.web.resources

import io.github.luaprogrammer.digital_bank.IntegrationTest
import io.github.luaprogrammer.digital_bank.objects.AccountObject.createAccountRequest
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AccountResourceTest : IntegrationTest() {

    companion object {
        private const val URI = "/bank-digital/v1/account"
    }

    @Test
    fun `You should seek a successful account`() {
        val accountNumber = "5773-3133-585332-1"

        mockMvc.perform(
            get("$URI/find")
                .param("accountNumber", accountNumber)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.accountNumber").value(accountNumber))
            .andExpect(jsonPath("$.balance").value(0.00))
    }

    @Test
    fun `You should not find an account`() {
        val accountNumber = "5773-3133-585331-2"

        mockMvc.perform(
            get("$URI/find")
                .param("accountNumber", accountNumber)
        )
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.message").value("Conta não encontrada: $accountNumber"))
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.timestamp").exists())
    }

    @Test
    fun `You should create an account successfully`() {
        val requestBody = createAccountRequest()

        mockMvc.perform(
            post("$URI/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andExpect(status().isCreated)
    }
}