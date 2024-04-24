package com.rpsouza.modules.company.controllers

import com.rpsouza.modules.company.dto.CreateJobDTO
import com.rpsouza.modules.company.model.JobEntity
import com.rpsouza.modules.company.useCases.CreateJobUseCase
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/job")
class JobController {

  @Autowired
  private lateinit var createJobUseCase: CreateJobUseCase

  @PostMapping("/")
  fun create(
    @Valid @RequestBody createJobDTO: CreateJobDTO,
    request: HttpServletRequest
  ): ResponseEntity<Any> {

    return try {
      val companyId = request.getAttribute("company_id").toString()

      val jobEntity = JobEntity(
        companyId = companyId,
        level = createJobDTO.level,
        benefits = createJobDTO.benefits,
        description = createJobDTO.description,
      )

      val result = createJobUseCase.execute(jobEntity)
      ResponseEntity.ok().body(result)
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
    }
  }
}