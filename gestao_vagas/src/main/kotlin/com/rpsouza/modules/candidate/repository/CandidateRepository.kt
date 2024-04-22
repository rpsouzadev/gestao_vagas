package com.rpsouza.modules.candidate.repository

import com.rpsouza.modules.candidate.model.CandidateEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CandidateRepository : JpaRepository<CandidateEntity, UUID> {

  fun findByUsernameOrEmail(username: String, email: String): CandidateEntity?
}