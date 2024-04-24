package com.rpsouza.modules.candidate.controllers

import com.rpsouza.modules.candidate.model.CandidateEntity
import com.rpsouza.modules.candidate.useCases.CreateCandidateUseCase
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/candidate")
class CandidateController {

  @Autowired
  private lateinit var createCandidateUseCase: CreateCandidateUseCase

  @Autowired
  private lateinit var passwordEncoder: PasswordEncoder

  @PostMapping("/")
  fun create(@Valid @RequestBody candidateEntity: CandidateEntity): ResponseEntity<Any> {
    return try {
      val password = passwordEncoder.encode(candidateEntity.password)
      candidateEntity.password = password

      val result = createCandidateUseCase.execute(candidateEntity)
      ResponseEntity.ok().body(result)
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
    }
  }
}