package com.rpsouza.modules.company.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity(name = "job")
data class JobEntity(

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  var id: String = "",

  @ManyToOne
  @field:JsonIgnore
  @JoinColumn(name = "company_id", insertable = false, updatable = false)
  val companyEntity: CompanyEntity? = null,

  @Column(name = "company_id", nullable = false)
  var companyId: String = "",
  var description: String = "",
  var benefits: String = "",

  @field:NotBlank(message = "Esse campo é obrigatório")
  var level: String = "",

  @field:CreationTimestamp
  var createdAt: LocalDateTime = LocalDateTime.now()
)