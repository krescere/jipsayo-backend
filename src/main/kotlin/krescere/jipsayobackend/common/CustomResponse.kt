package krescere.jipsayobackend.common

import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class CustomResponse(
    private var status: HttpStatus,
    private var body: CustomBody
) {
    fun toResponseEntity() : ResponseEntity<CustomBody> {
        return ResponseEntity
            .status(status)
            .body(body)
    }
}