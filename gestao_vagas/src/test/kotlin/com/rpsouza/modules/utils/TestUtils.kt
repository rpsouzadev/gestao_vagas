package com.rpsouza.modules.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.rpsouza.roles.Role
import java.time.Duration
import java.time.Instant
import java.util.UUID

class TestUtils {

  companion object {
    fun objectToJSON(obj: Any): String {
      return try {
        val objectMapper = ObjectMapper()
        objectMapper.writeValueAsString(obj)
      } catch (ex: Exception) {
        throw RuntimeException(ex)
      }
    }

    fun generateToken(idCompany: UUID, secret: String): String {
      val algorithm: Algorithm = Algorithm.HMAC256(secret)
      val expiresIn = Instant.now().plus(Duration.ofHours(2))

      val token = JWT.create()
        .withExpiresAt(expiresIn)
        .withClaim("roles", arrayListOf(Role.COMPANY))
        .withIssuer("kovagas")
        .withSubject(idCompany.toString())
        .sign(algorithm)

      return token
    }
  }
}