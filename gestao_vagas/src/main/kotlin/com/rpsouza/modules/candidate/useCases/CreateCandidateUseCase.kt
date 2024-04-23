package com.rpsouza.modules.candidate.useCases

import com.rpsouza.exceptions.UserFoundException
import com.rpsouza.modules.candidate.model.CandidateEntity
import com.rpsouza.modules.candidate.repository.CandidateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateCandidateUseCase {

  @Autowired
  private lateinit var candidateRepository: CandidateRepository

  fun execute(candidateEntity: CandidateEntity): CandidateEntity {
    candidateRepository.findByUsernameOrEmail(
      candidateEntity.username,
      candidateEntity.email
    )?.let {
      throw UserFoundException()
    }

    return candidateRepository.save(candidateEntity)
  }
}