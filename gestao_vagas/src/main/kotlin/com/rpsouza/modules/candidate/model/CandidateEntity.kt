package com.rpsouza.modules.candidate.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime

@Entity(name = "candidate")
data class CandidateEntity(

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  var id: String = "",

  var name: String = "",

  @field:NotBlank
  @field:Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço.")
  var username: String = "",

  @field:Email(message = "O campo [email] deve conter um e-mail válido")
  var email: String = "",

  @field:Length(min = 6, max = 100, message = "A senha deve conter entre (6) e (100) caracteres")
  var password: String = "",
  var description: String = "",
  var curriculum: String = "",

  @field:CreationTimestamp
  var createdAt: LocalDateTime = LocalDateTime.now()
)
