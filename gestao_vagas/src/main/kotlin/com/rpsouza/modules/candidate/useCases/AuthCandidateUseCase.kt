package com.rpsouza.modules.candidate.useCases

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.rpsouza.modules.candidate.dto.AuthCandidateRequestDTO
import com.rpsouza.modules.candidate.dto.AuthCandidateResponseDTO
import com.rpsouza.modules.candidate.repository.CandidateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import javax.naming.AuthenticationException

@Service
class AuthCandidateUseCase {
  @Value("\${security.token.secret.candidate}")
  private lateinit var secretKey: String

  @Autowired
  private lateinit var candidateRepository: CandidateRepository

  @Autowired
  private lateinit var passwordEncoder: PasswordEncoder

  fun invoke(authCandidateRequestDTO: AuthCandidateRequestDTO): AuthCandidateResponseDTO {
    val candidate = candidateRepository.findByUsername(
      authCandidateRequestDTO.username
    )

    if (candidate == null) {
      throw UsernameNotFoundException("Username or password incorrect")
    }

    val passwordMatches = passwordEncoder.matches(
      authCandidateRequestDTO.password,
      candidate.password
    )

    if (!passwordMatches) {
      throw AuthenticationException()
    }

    val algorithm: Algorithm = Algorithm.HMAC256(secretKey)

    val token = JWT.create()
      .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
      .withClaim("roles", arrayListOf("cadidate"))
      .withIssuer("kovagas")
      .withSubject(candidate.id)
      .sign(algorithm)

    val authCandidateResponse = AuthCandidateResponseDTO(
      access_token = token
    )

    return authCandidateResponse
  }
}