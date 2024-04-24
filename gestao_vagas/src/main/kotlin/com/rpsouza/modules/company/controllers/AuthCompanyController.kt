package com.rpsouza.modules.company.controllers

import com.rpsouza.modules.company.dto.AuthCompanyDTO
import com.rpsouza.modules.company.useCases.AuthCompanyUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthCompanyController {

  @Autowired
  private lateinit var authCompanyUseCase: AuthCompanyUseCase

  @PostMapping("/company")
  fun create(@RequestBody authCompanyDTO: AuthCompanyDTO): ResponseEntity<String> {

    return try {
      val result = authCompanyUseCase.invoke(authCompanyDTO)
      ResponseEntity.ok().body(result)
    } catch (ex: Exception) {
      ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.message)
    }
  }
}