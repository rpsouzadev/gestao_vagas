package com.rpsouza.modules.company.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity(name = "job")
data class JobEntity(

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  var id: String = "",

  @ManyToOne
  @JoinColumn(name = "company_id")
  val companyEntity: CompanyEntity,

//  @Column(name = "company_id", insertable = false, updatable = false)
//  var companyId: UUID,
  var description: String = "",
  var benefits: String = "",
  var level: String = "",

  @field:CreationTimestamp
  var createdAt: LocalDateTime = LocalDateTime.now()
)
