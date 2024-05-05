package com.rpsouza.modules.company.useCases

import com.rpsouza.exceptions.CompanyNotFoundException
import com.rpsouza.modules.company.model.JobEntity
import com.rpsouza.modules.company.repositories.CompanyRepository
import com.rpsouza.modules.company.repositories.JobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateJobUseCase {

  @Autowired
  private lateinit var jobRepository: JobRepository

  @Autowired
  private lateinit var companyRepository: CompanyRepository

  fun execute(jobEntity: JobEntity): JobEntity {
    companyRepository.findById(jobEntity.companyId!!).orElseThrow {
      throw CompanyNotFoundException()
    }

    return jobRepository.save(jobEntity)
  }
}