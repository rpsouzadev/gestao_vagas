package com.rpsouza.exceptions

class CompanyNotFoundException(override val message: String = "Company not found") :
  RuntimeException(message) {
}