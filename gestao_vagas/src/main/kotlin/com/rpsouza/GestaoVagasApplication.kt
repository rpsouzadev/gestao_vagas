package com.rpsouza

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(
  info = Info(
    title = "Gestão de vagas",
    description = "API responsável pela gestão de vagas",
    version = "1"
  )
)
@SpringBootApplication
class GestaoVagasApplication

fun main(args: Array<String>) {
  runApplication<GestaoVagasApplication>(*args)
}
