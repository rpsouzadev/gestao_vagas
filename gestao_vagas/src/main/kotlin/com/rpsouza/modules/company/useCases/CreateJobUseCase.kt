package com.rpsouza.modules.company.useCases

import com.rpsouza.modules.company.model.JobEntity
import com.rpsouza.modules.company.repositories.JobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateJobUseCase {

  @Autowired
  private lateinit var jobRepository: JobRepository

  fun execute (jobEntity: JobEntity): JobEntity {
    return jobRepository.save(jobEntity)
  }
}