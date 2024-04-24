package com.rpsouza.providers

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class JWTProvider {

  @Value("\${security.token.secret}")
  private lateinit var secretKey: String

  fun validateToken(token: String): String {
    val tokenReplace = token.replace("Bearer", "").trim()

    val algorithm: Algorithm = Algorithm.HMAC256(secretKey)

    return try {
      JWT.require(algorithm)
        .build()
        .verify(tokenReplace)
        .subject
    } catch (ex: JWTVerificationException) {
      ex.printStackTrace()
      ""
    }
  }
}