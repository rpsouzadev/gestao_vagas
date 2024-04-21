package com.rpsouza.exceptions

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlerController(private val messageSource: MessageSource) {


  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleMethodArgumentNotValidException(
    ex: MethodArgumentNotValidException
  ): ResponseEntity<List<ErrorMessageDTO>> {

    val dto: MutableList<ErrorMessageDTO> = ArrayList()

    ex.bindingResult.fieldErrors.forEach { fieldError ->
      val message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())

      dto.add(ErrorMessageDTO(message, fieldError.field))
    }

    return ResponseEntity(dto, HttpStatus.BAD_REQUEST)
  }
}