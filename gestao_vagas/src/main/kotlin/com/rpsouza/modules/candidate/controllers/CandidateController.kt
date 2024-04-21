package com.rpsouza.modules.candidate.controllers

import com.rpsouza.modules.candidate.model.CandidateEntity
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/candidate")
class CandidateController {

  @PostMapping("/")
  fun create(@Valid @RequestBody candidateEntity: CandidateEntity) {
    println(candidateEntity.email)
  }
}