package com.serey.karpiaelearningapi.shared.response

import java.time.Instant

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T?,
    val timestamp: Instant = Instant.now(),
    val errors: Map<String, String>? = null
) {
    companion object {
        fun <T> success(mesage: String, data: T? = null): ApiResponse<T> {
            return ApiResponse(
                success = true,
                message = mesage,
                data = data,
            )
        }

        fun failure(
            message: String,
            errors: Map<String, String>? = null
        ): ApiResponse<Nothing> {
            return ApiResponse(
                success = false,
                message = message,
                data = null,
                errors = errors
            )
        }
    }

}
