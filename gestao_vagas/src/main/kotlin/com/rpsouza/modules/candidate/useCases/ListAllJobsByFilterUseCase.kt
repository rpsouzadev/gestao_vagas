package com.rpsouza.modules.candidate.useCases

import com.rpsouza.modules.company.model.JobEntity
import com.rpsouza.modules.company.repositories.JobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ListAllJobsByFilterUseCase {

  @Autowired
  private lateinit var jobRepository: JobRepository

  fun invoke(search: String): List<JobEntity> {
    return jobRepository.findByDescriptionLike(search)
  }

}