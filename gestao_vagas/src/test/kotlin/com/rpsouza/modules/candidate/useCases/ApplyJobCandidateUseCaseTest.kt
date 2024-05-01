package com.rpsouza.modules.candidate.useCases

import com.rpsouza.exceptions.UserNotFoundException
import com.rpsouza.modules.candidate.repository.CandidateRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class ApplyJobCandidateUseCaseTest {

  @InjectMocks
  private lateinit var applyJobCandidateUseCase: ApplyJobCandidateUseCase

  @Mock
  private lateinit var candidateRepository: CandidateRepository

  @Mock
  private lateinit var jobRepository: CandidateRepository

  @Test
  @DisplayName("Should not be able to apply job with candidate not found")
  fun should_not_be_able_to_apply_job_with_candidate_not_found() {
    val uuid = UUID.fromString("335e8030-8708-4d78-955e-7e121f7a01c9")

    try {
      applyJobCandidateUseCase.invoke(uuid, uuid)
    } catch (ex: Exception) {
      assertThat(ex).isInstanceOf(UserNotFoundException::class.java)
    }
  }
}