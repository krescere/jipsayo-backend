package krescere.jipsayobackend.common

import krescere.jipsayobackend.common.error.CustomError
import java.time.LocalDateTime

class CustomBody(
    private val data: Any? = null,
    private val message: String? = null,
    private val timestamp: LocalDateTime = LocalDateTime.now(),
    private val errors: List<CustomError>? = null
)