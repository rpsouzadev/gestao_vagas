package com.rpsouza

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(
  info = Info(
    title = "Gestão de vagas",
    description = "API responsável pela gestão de vagas",
    version = "1"
  )
)
@SecurityScheme(
  name = "jwt_auth",
  scheme = "bearer",
  bearerFormat = "JWT",
  type = SecuritySchemeType.HTTP,
  `in` = SecuritySchemeIn.HEADER
)
@SpringBootApplication
class GestaoVagasApplication

fun main(args: Array<String>) {
  runApplication<GestaoVagasApplication>(*args)
}
