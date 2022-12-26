package krescere.jipsayobackend.common.error

import com.google.gson.Gson
import com.google.gson.JsonObject
import krescere.jipsayobackend.common.CustomBody
import krescere.jipsayobackend.common.CustomResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    // CustomException
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(exception: CustomException): ResponseEntity<CustomBody> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(CustomBody(
                errors = listOf(
                    CustomException(
                        message = exception.message,
                        code = exception.code
                    ).toCustomError()
                )
            ))
    }

    // 나머지 예외 처리
    @ExceptionHandler(Exception::class)
    fun handleOtherException(exception: Exception): ResponseEntity<CustomBody> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(CustomBody(
                errors = listOf(
                    CustomException(
                        message = exception.message,
                        code = ErrorCode.INTERNAL_SERVER_ERROR
                    ).toCustomError()
                )
            ))
    }
}