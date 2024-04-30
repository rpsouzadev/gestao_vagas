package com.rpsouza.modules.company.dto

import io.swagger.v3.oas.annotations.media.Schema

data class CreateJobDTO(

  @Schema(
    example = "Junior",
    requiredMode = Schema.RequiredMode.REQUIRED
  )
  val level: String,

  @Schema(
    example = "Gympass, Plano de saúde",
    requiredMode = Schema.RequiredMode.REQUIRED
  )
  val benefits: String,

  @Schema(
    example = "Vaga para pessoa desenvolvedora Kotlin junior",
    requiredMode = Schema.RequiredMode.REQUIRED
  )
  val description: String,
)
