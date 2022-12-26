package krescere.jipsayobackend.common

import krescere.jipsayobackend.common.error.CustomError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.sql.Timestamp

class CustomResponse(
    private val status: HttpStatus,
    private val body: CustomBody
) {
    fun toResponseEntity() : ResponseEntity<CustomBody> {
        return ResponseEntity
            .status(status)
            .body(body)
    }
}