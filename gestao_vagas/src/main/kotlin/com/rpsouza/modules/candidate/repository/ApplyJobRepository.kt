package com.rpsouza.modules.candidate.repository

import com.rpsouza.modules.candidate.model.ApplyJobEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ApplyJobRepository : JpaRepository<ApplyJobEntity, UUID>