package com.rpsouza.exceptions

class JobNotFoundException(override val message: String = "Job Not Found") :
  RuntimeException(message)