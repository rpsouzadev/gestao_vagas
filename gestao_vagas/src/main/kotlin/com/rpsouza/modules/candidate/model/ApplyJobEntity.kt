package com.rpsouza.modules.candidate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.rpsouza.modules.company.model.JobEntity
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "apply_jobs")
class ApplyJobEntity(

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  var id: UUID = UUID.randomUUID(),

  @ManyToOne
  @field:JsonIgnore
  @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
  var candidateEntity: CandidateEntity? = null,

  @ManyToOne
  @field:JsonIgnore
  @JoinColumn(name = "job_id", insertable = false, updatable = false)
  var jobEntity: JobEntity? = null,

  @Column(name = "candidate_id", nullable = false)
  var candidateId: UUID? = null,

  @Column(name = "job_id", nullable = false)
  var jobId: UUID? = null,

  @field:CreationTimestamp
  var createdAt: LocalDateTime = LocalDateTime.now()
)