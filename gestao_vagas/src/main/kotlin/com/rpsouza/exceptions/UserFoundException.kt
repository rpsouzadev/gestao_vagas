package com.rpsouza.exceptions

class UserFoundException : RuntimeException {
  constructor(): super("Usuário já existe")
  constructor(exception: String?): super(exception)
}