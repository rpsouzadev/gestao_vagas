package com.rpsouza.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

  @Bean
  fun openApi(): OpenAPI {
    return OpenAPI()
      .info(
        Info()
          .title("Gestão de vagas")
          .description("API responsável pela gestão de vagas")
          .version("1")
      )
      .schemaRequirement("Bearer Authentication", createSecurityScheme())
  }

  @Bean
  fun createSecurityScheme(): SecurityScheme {
    return SecurityScheme()
      .name("jwt_auth")
      .scheme("bearer")
      .bearerFormat("JWT")
      .type(SecurityScheme.Type.HTTP)
      .`in`(SecurityScheme.In.HEADER)
  }
}
