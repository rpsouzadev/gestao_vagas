package com.rpsouza.modules.candidate.controllers

import com.rpsouza.modules.candidate.dto.AuthCandidateRequestDTO
import com.rpsouza.modules.candidate.useCases.AuthCandidateUseCase
import com.rpsouza.modules.candidate.useCases.CreateCandidateUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/candidate")
class AuthCandidateController {

  @Autowired
  private lateinit var authCandidateUseCase: AuthCandidateUseCase

  @PostMapping("/auth")
  fun auth(@RequestBody authCandidateRequestDTO: AuthCandidateRequestDTO): ResponseEntity<Any> {
    return try {
      val token = authCandidateUseCase.invoke(authCandidateRequestDTO)

      ResponseEntity.ok().body(token)
    } catch (ex: Exception) {
      ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.message)
    }
  }
}