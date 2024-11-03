package io.github.luaprogrammer.digital_bank.adapters.input.config

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenApiConfig {

    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("v1")
            .packagesToScan("io.github.luaprogrammer.digital_bank.adapters.input.web.resources")
            .build();
    }

    @Bean
    fun springOpenApi(): OpenAPI {
        return OpenAPI().info(info());
    }

    private fun info(): Info {
        return Info()
            .title("Sistema Bancário")
            .description("Sistema de transação bancária")
            .version("1.0.0")
    }
}
