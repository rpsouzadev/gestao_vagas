package com.rpsouza.modules.candidate

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length
import java.util.UUID

data class CandidateEntity(
  var id: Long = 0,
  var name: String = "",

  @field:Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço.")
  var username: String = "",

  @field:Email(message = "O campo [email] deve conter um e-mail válido")
  val email: String,

  @field:Length(min = 6, max = 12, message = "A senha deve conter entre (6) e (12) caracteres")
  var password: String = "",
  var description: String = "",
  var curriculum: String = "",
)
