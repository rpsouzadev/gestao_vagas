package com.rpsouza.modules.candidate.controllers

import com.rpsouza.modules.candidate.dto.ProfileCandidateResponseDTO
import com.rpsouza.modules.candidate.model.CandidateEntity
import com.rpsouza.modules.candidate.useCases.ApplyJobCandidateUseCase
import com.rpsouza.modules.candidate.useCases.CreateCandidateUseCase
import com.rpsouza.modules.candidate.useCases.ListAllJobsByFilterUseCase
import com.rpsouza.modules.candidate.useCases.ProfileCandidateUseCase
import com.rpsouza.modules.company.model.JobEntity
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
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
class CandidateController {
  @Autowired
  private lateinit var createCandidateUseCase: CreateCandidateUseCase

  @Autowired
  private lateinit var profileCandidateUseCase: ProfileCandidateUseCase

  @Autowired
  private lateinit var passwordEncoder: PasswordEncoder

  @Autowired
  private lateinit var listAllJobsByFilterUseCase: ListAllJobsByFilterUseCase

  @Autowired
  private lateinit var applyJobCandidateUseCase: ApplyJobCandidateUseCase

  @PostMapping("/")
  @Operation(
    summary = "Cadastro de candidato",
    description = "Essa rota é responsavel por cadastrar um novo candiato"
  )
  @ApiResponses(
    ApiResponse(
      responseCode = "200",
      content = [Content(schema = Schema(implementation = CandidateEntity::class))]
    ),
    ApiResponse(
      responseCode = "400",
      description = "Usuário já existe"
    )
  )
  fun create(@Valid @RequestBody candidateEntity: CandidateEntity): ResponseEntity<Any> {
    return try {
      val password = passwordEncoder.encode(candidateEntity.password)
      candidateEntity.password = password

      val result = createCandidateUseCase.execute(candidateEntity)
      ResponseEntity.ok().body(result)
    } catch (ex: Exception) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }
  }

  @GetMapping("/")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(
    summary = "Perfil do candidato",
    description = "Essa rota é responsavel por buscar informações do perfil do candidato"
  )
  @ApiResponses(
    ApiResponse(
      responseCode = "200",
      content = [Content(schema = Schema(implementation = ProfileCandidateResponseDTO::class))]
    ),
    ApiResponse(
      responseCode = "400",
      description = "User not found"
    )
  )
  @SecurityRequirement(name = "jwt_auth")
  fun get(request: HttpServletRequest): ResponseEntity<Any> {
    val candidateId = request.getAttribute("candidate_id").toString()

    return try {
      val result = profileCandidateUseCase.invoke(candidateId)

      ResponseEntity.ok().body(result)
    } catch (ex: Exception) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }
  }

  @GetMapping("/jobs")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(
    summary = "Listagem de vagas disponíveis para o candidato",
    description = "Essa rota é responsavel por listar todas as vagas disponíveis, baseado no search"
  )
  @ApiResponses(
    ApiResponse(
      responseCode = "200",
      content = [Content(array = ArraySchema(schema = Schema(implementation = JobEntity::class)))]
    )
  )
  @SecurityRequirement(name = "jwt_auth")
  fun findJobsByFilter(search: String): List<JobEntity> {
    return listAllJobsByFilterUseCase.invoke(search)
  }

  @PostMapping("/job/apply")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(
    summary = "Inscrição do canditado para uma vaga",
    description = "Essa função é responsável por realizar a inscrição do candidato a uma vaga"
  )
  fun applyJob(resquest: HttpServletRequest, @RequestBody idJob: UUID): ResponseEntity<Any> {
    val idCandidate = resquest.getAttribute("candidate_id")

    return try {
      val result = applyJobCandidateUseCase.invoke(UUID.fromString(idCandidate.toString()), idJob)

      ResponseEntity.ok().body(result)
    } catch (ex: Exception) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }
  }
}