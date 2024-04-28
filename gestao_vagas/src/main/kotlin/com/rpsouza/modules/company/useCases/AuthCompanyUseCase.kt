package com.rpsouza.modules.company.useCases

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.rpsouza.modules.company.dto.AuthCompanyDTO
import com.rpsouza.modules.company.dto.AuthCompanyResponseDTO
import com.rpsouza.modules.company.repositories.CompanyRepository
import com.rpsouza.roles.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import javax.naming.AuthenticationException

@Service
class AuthCompanyUseCase {
  @Value("\${security.token.secret}")
  private lateinit var secretKey: String

  @Autowired
  private lateinit var companyRepository: CompanyRepository

  @Autowired
  private lateinit var passwordEncoder: PasswordEncoder

  fun invoke(authCompanyDTO: AuthCompanyDTO): AuthCompanyResponseDTO {
    val company = companyRepository.findByUsername(
      authCompanyDTO.username
    )

    if (company == null) {
      throw UsernameNotFoundException("Username or password incorrect")
    }

    val passwordMatches = passwordEncoder.matches(authCompanyDTO.password, company.password)

    if (!passwordMatches) {
      throw AuthenticationException()
    }

    val algorithm: Algorithm = Algorithm.HMAC256(secretKey)
    val expiresIn = Instant.now().plus(Duration.ofHours(2))

    val token = JWT.create()
      .withExpiresAt(expiresIn)
      .withClaim("roles", arrayListOf(Role.COMPANY))
      .withIssuer("kovagas")
      .withSubject(company.id.toString())
      .sign(algorithm)

    val authCompanyResponseDTO = AuthCompanyResponseDTO(
      access_token = token,
      expires_in = expiresIn.toEpochMilli()
    )

    return authCompanyResponseDTO
  }
}