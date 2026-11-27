package com.serey.karpiaelearningapi.shared.exception

import com.serey.karpiaelearningapi.shared.response.ApiResponse
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(e: ResourceNotFoundException):
            ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ApiResponse.failure(
                message = e.message ?: "Resource not found",
                )
            )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        e: MethodArgumentNotValidException
    ): ResponseEntity<ApiResponse<Nothing>> {
        val errors = e.bindingResult.fieldErrors.associate {
            it.field to (it.defaultMessage ?: "Invalid value")
        }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ApiResponse.failure(
                    message = "Validation failed",
                    errors = errors
                )
            )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleInvalidRequestException(
        e: HttpMessageNotReadableException
    ): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ApiResponse.failure(
                    message = e.message ?: "Invalid request",
                )
            )
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(
        e: DataIntegrityViolationException
    ): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(
                ApiResponse.failure(
                    message = e.message ?: "Database constraint violation",
                )
            )
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(
        e: Exception
    ): ResponseEntity<ApiResponse<Nothing>> {
        e.printStackTrace()
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ApiResponse.failure(
                    message = e.message ?: "An unexpected error occurred",
                )
            )
    }

    @ExceptionHandler(DuplicateResourceException::class)
    fun handleDuplicateEmailException(
        e: DuplicateResourceException
    ): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ApiResponse.failure(
                message = e.message ?: "Resource already exists",
            )
        )
    }

    @ExceptionHandler(UsernameAlreadyExistException::class)
    fun handleUsernameAlreadyExistException(
        e: UsernameAlreadyExistException
    ): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(
                ApiResponse.failure(
                    message = e.message ?: "Username already exists",
                )
            )
    }
}
