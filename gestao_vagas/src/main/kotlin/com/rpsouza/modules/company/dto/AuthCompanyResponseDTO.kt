package com.rpsouza.modules.company.dto

import com.fasterxml.jackson.annotation.JsonAlias

data class AuthCompanyResponseDTO (
  val access_token: String,
  val expires_in: Long,
)