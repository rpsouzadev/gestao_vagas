package com.rpsouza.modules.company.controllers

import com.rpsouza.modules.company.model.CompanyEntity
import com.rpsouza.modules.company.useCases.CreateCompanyUseCase
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
@RequestMapping("/company")
class CompanyController {

  @Autowired
  private lateinit var createCompanyUseCase: CreateCompanyUseCase

  @PostMapping("/")
  fun create(@Valid @RequestBody companyEntity: CompanyEntity): ResponseEntity<Any> {

    return try {
      val result = createCompanyUseCase.execute(companyEntity)
      ResponseEntity.ok().body(result)
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
    }
  }
}