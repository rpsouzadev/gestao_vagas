package com.rpsouza.modules.company.useCases

import com.rpsouza.exceptions.UserFoundException
import com.rpsouza.modules.company.model.CompanyEntity
import com.rpsouza.modules.company.repositories.CompanyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CreateCompanyUseCase {

  @Autowired
  private lateinit var companyRepository: CompanyRepository

  @Autowired
  private lateinit var passwordEncoder: PasswordEncoder

  fun execute(companyEntity: CompanyEntity): CompanyEntity {
    companyRepository.findByUsernameOrEmail(
      companyEntity.username,
      companyEntity.email
    )?.let {
      throw UserFoundException()
    }

    val password = passwordEncoder.encode(companyEntity.password)
    companyEntity.password = password

    return companyRepository.save(companyEntity)
  }

}