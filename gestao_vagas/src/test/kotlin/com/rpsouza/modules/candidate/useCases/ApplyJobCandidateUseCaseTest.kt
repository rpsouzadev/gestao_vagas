package com.rpsouza.modules.candidate.useCases

import com.rpsouza.exceptions.JobNotFoundException
import com.rpsouza.exceptions.UserNotFoundException
import com.rpsouza.modules.candidate.model.ApplyJobEntity
import com.rpsouza.modules.candidate.model.CandidateEntity
import com.rpsouza.modules.candidate.repository.ApplyJobRepository
import com.rpsouza.modules.candidate.repository.CandidateRepository
import com.rpsouza.modules.company.model.JobEntity
import com.rpsouza.modules.company.repositories.JobRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class ApplyJobCandidateUseCaseTest {

  @InjectMocks
  private lateinit var applyJobCandidateUseCase: ApplyJobCandidateUseCase

  @Mock
  private lateinit var candidateRepository: CandidateRepository

  @Mock
  private lateinit var jobRepository: JobRepository

  @Mock
  private lateinit var applyJobRepository: ApplyJobRepository


  @Test
  @DisplayName("Should not be able to apply job with candidate not found")
  fun should_not_be_able_to_apply_job_with_candidate_not_found() {
    val uuid = UUID.randomUUID()

    try {
      applyJobCandidateUseCase.invoke(uuid, uuid)
    } catch (ex: Exception) {
      assertThat(ex).isInstanceOf(UserNotFoundException::class.java)
    }
  }

  @Test
  @DisplayName("Should not be able to apply job with job not found")
  fun should_not_be_able_to_apply_job_with_job_not_found() {
    val idCandidate = UUID.randomUUID()
    val candidate = CandidateEntity(id = idCandidate)

    `when`(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate))

    try {
      applyJobCandidateUseCase.invoke(idCandidate, idCandidate)
    } catch (ex: Exception) {
      assertThat(ex).isInstanceOf(JobNotFoundException::class.java)
    }
  }

  @Test
  fun should_be_able_to_create_a_new_apply_job() {
    val idCandidate = UUID.randomUUID()
    val idJob = UUID.randomUUID()

    val applyJob = ApplyJobEntity(candidateId = idCandidate, jobId = idJob)

    `when`(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(CandidateEntity()))
    `when`(jobRepository.findById(idJob)).thenReturn(Optional.of(JobEntity()))

    `when`(applyJobRepository.save(any())).thenReturn(applyJob)

    val result = applyJobCandidateUseCase.invoke(idCandidate, idJob)

    assertThat(result).hasFieldOrProperty("id")
    assertNotNull(result.id)
  }

}