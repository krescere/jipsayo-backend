package krescere.jipsayobackend.common.error

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler(private val gson: Gson) {
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<Any> {
        val jsonObject = JsonObject()
        jsonObject.addProperty("error", e.message)
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(gson.toJson(jsonObject))
    }

    @ExceptionHandler(Exception::class)
    fun handleOtherException(e: Exception): ResponseEntity<Any> {
        val jsonObject = JsonObject()
        jsonObject.addProperty("error", e.message)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(gson.toJson(jsonObject))
    }
}