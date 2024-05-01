package com.rpsouza.modules.candidate.useCases

import com.rpsouza.exceptions.JobNotFoundException
import com.rpsouza.exceptions.UserNotFoundException
import com.rpsouza.modules.candidate.repository.CandidateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ApplyJobCandidateUseCase {

  @Autowired
  private lateinit var candidateRepository: CandidateRepository

  @Autowired
  private lateinit var jobRepository: CandidateRepository

  fun invoke(idCandidate: UUID, idJob: UUID) {
    candidateRepository.findById(idCandidate).orElseThrow {
      throw UserNotFoundException()
    }

    jobRepository.findById(idJob).orElseThrow {
      throw JobNotFoundException()
    }
  }
}