package com.rpsouza.modules.candidate.dto

import com.fasterxml.jackson.annotation.JsonAlias

data class AuthCandidateResponseDTO(
  val access_token: String,
  val expires_in: Long,
)
