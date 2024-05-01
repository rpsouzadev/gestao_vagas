package com.rpsouza.exceptions

class UserNotFoundException(override val message: String = "User Not Found") :
  RuntimeException(message)