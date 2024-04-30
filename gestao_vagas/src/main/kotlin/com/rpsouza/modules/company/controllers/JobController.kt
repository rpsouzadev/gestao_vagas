package com.rpsouza.modules.company.controllers

import com.rpsouza.modules.company.dto.CreateJobDTO
import com.rpsouza.modules.company.model.JobEntity
import com.rpsouza.modules.company.useCases.CreateJobUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/company/job")
class JobController {

  @Autowired
  private lateinit var createJobUseCase: CreateJobUseCase

  @PostMapping("/")
  @PreAuthorize("hasRole('COMPANY')")
  @Tag(name = "Vagas", description = "Informações das vagas")
  @Operation(
    summary = "Cadastro de vagas",
    description = "Essa rota é responsavel por cadastrar as vagas dentro da empresa"
  )
  @ApiResponses(
    ApiResponse(
      responseCode = "200",
      content = [Content(schema = Schema(implementation = JobEntity::class))]
    )
  )
  @SecurityRequirement(name = "jwt_auth")
  fun create(
    @Valid @RequestBody createJobDTO: CreateJobDTO,
    request: HttpServletRequest
  ): ResponseEntity<Any> {

    return try {
      val companyId = request.getAttribute("company_id").toString()

      val jobEntity = JobEntity(
        companyId = UUID.fromString(companyId),
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