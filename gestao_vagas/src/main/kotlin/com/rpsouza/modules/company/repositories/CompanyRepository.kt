package com.rpsouza.modules.company.repositories

import com.rpsouza.modules.company.model.CompanyEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CompanyRepository : JpaRepository<CompanyEntity, UUID> {

  fun findByUsernameOrEmail(username: String, email: String): CompanyEntity?
  fun findByUsername(username: String): CompanyEntity?
}
