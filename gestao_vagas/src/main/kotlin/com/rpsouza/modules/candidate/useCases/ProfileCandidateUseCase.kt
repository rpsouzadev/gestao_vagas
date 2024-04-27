package com.rpsouza.modules.candidate.useCases

import com.rpsouza.modules.candidate.dto.ProfileCandidateResponseDTO
import com.rpsouza.modules.candidate.repository.CandidateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ProfileCandidateUseCase {

  @Autowired
  private lateinit var candidateRepository: CandidateRepository

  fun invoke(candidateId: String): ProfileCandidateResponseDTO? {
    val candidate = candidateRepository.findById(UUID.fromString(candidateId))
      .orElseThrow {
        ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
      }

    val profileCandidateResponse = ProfileCandidateResponseDTO(
      candidate.id.toString(),
      candidate.name,
      candidate.email,
      candidate.username,
      candidate.description
    )

    return profileCandidateResponse
  }
}