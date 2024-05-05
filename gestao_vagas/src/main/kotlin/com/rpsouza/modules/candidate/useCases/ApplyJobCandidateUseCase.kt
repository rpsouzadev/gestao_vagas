package com.rpsouza.modules.candidate.useCases

import com.rpsouza.exceptions.JobNotFoundException
import com.rpsouza.exceptions.UserNotFoundException
import com.rpsouza.modules.candidate.model.ApplyJobEntity
import com.rpsouza.modules.candidate.repository.ApplyJobRepository
import com.rpsouza.modules.candidate.repository.CandidateRepository
import com.rpsouza.modules.company.repositories.JobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ApplyJobCandidateUseCase {

  @Autowired
  private lateinit var candidateRepository: CandidateRepository

  @Autowired
  private lateinit var jobRepository: JobRepository

  @Autowired
  private lateinit var applyJobRepository: ApplyJobRepository


  fun invoke(idCandidate: UUID, idJob: UUID): ApplyJobEntity {
    candidateRepository.findById(idCandidate).orElseThrow {
      throw UserNotFoundException()
    }

    jobRepository.findById(idJob).orElseThrow {
      throw JobNotFoundException()
    }

    val applyJobEntity = ApplyJobEntity(candidateId = idCandidate, jobId = idJob)

    return applyJobRepository.save(applyJobEntity)
  }
}