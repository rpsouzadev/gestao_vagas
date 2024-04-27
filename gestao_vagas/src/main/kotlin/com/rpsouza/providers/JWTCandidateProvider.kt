package com.rpsouza.providers

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class JWTCandidateProvider {

  @Value("\${security.token.secret.candidate}")
  private lateinit var secretKey: String

  fun validateToken(token: String): DecodedJWT? {
    val tokenReplace = token.replace("Bearer", "").trim()

    val algorithm: Algorithm = Algorithm.HMAC256(secretKey)

    return try {
      JWT.require(algorithm).build().verify(tokenReplace)
    } catch (ex: JWTVerificationException) {
      ex.printStackTrace()
      null
    }
  }
}