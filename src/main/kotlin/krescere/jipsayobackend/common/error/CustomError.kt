package krescere.jipsayobackend.common.error

class CustomError(
    private val message: String?,
    private val code: ErrorCode?,
    private val detail: String? = null
)