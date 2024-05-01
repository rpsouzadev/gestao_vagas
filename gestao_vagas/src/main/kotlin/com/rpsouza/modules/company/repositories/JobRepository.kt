package com.rpsouza.modules.company.repositories

import com.rpsouza.modules.company.model.JobEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface JobRepository : JpaRepository<JobEntity, UUID> {

  fun findByDescriptionContainingIgnoreCase(search: String): List<JobEntity>
}