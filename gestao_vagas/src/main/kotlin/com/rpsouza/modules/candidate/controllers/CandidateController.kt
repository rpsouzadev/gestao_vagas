package com.rpsouza.modules.candidate.controllers

import com.rpsouza.exceptions.UserFoundException
import com.rpsouza.modules.candidate.model.CandidateEntity
import com.rpsouza.modules.candidate.repository.CandidateRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/candidate")
class CandidateController {

  @Autowired
  private lateinit var candidateRepository: CandidateRepository

  @PostMapping("/")
  fun create(@Valid @RequestBody candidateEntity: CandidateEntity): CandidateEntity {

    candidateRepository.findByUsernameOrEmail(
      candidateEntity.username,
      candidateEntity.email
    )?.let {
      throw UserFoundException()
    }

    return candidateRepository.save(candidateEntity)
  }
}