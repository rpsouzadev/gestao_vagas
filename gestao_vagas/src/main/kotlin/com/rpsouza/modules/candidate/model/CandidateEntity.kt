package com.rpsouza.modules.candidate.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime
import java.util.UUID

@Entity(name = "candidate")
data class CandidateEntity(

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  val id: String = "",

  val name: String,

  @field:Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço.")
  val username: String = "",

  @field:Email(message = "O campo [email] deve conter um e-mail válido")
  val email: String,

  @field:Length(min = 6, max = 12, message = "A senha deve conter entre (6) e (12) caracteres")
  val password: String,
  val description: String,
  val curriculum: String,

  @CreationTimestamp
  val createdAt: LocalDateTime,
)
