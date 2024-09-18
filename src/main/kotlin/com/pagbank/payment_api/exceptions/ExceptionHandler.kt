package com.pagbank.payment_api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    private fun createExceptionResponse(
        exception: Exception,
        request: WebRequest,
        status: HttpStatus
    ): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(
            exception.message,
            request.getDescription(false),
            Date()
        )

        return ResponseEntity<ExceptionResponse>(
            exceptionResponse,
            status
        )
    }

    @ExceptionHandler(InvalidPaymentMethodException::class)
    fun handleInvalidPaymentMethodException(
        exception: Exception,
        request: WebRequest
    ): ResponseEntity<ExceptionResponse> {
        logger.error("Invalid payment method: ${exception.message}", exception)
        return createExceptionResponse(exception, request, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NoPaymentFoundException::class)
    fun handleNoPaymentFoundException(
        exception: Exception,
        request: WebRequest
    ): ResponseEntity<ExceptionResponse> {
        logger.error("No elements found: ${exception.message}", exception)
        return createExceptionResponse(exception, request, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericExceptions(
        exception: Exception,
        request: WebRequest
    ): ResponseEntity<ExceptionResponse> {
        logger.error("An error occurred: ${exception.message}", exception)
        return createExceptionResponse(exception, request, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}