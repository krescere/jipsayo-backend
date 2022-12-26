package krescere.jipsayobackend.common.error

// 서비스상 오류가 발생했을때 사용 (백엔드가 사용)
class CustomException (
    code: ErrorCode, // 프론트가 식별자로 사용할 오류 코드
    message: String?, // 한글로 작성하는 오류 메시지
    detail: Any? = null // 오류 스택 트레이스
): RuntimeException() {
    // 오류코드만 반환시 메시지는 디폴트 메시지로 반환
    constructor(code: ErrorCode) : this(code, code.message, null)
    constructor(code: ErrorCode, message: String?) : this(code, message, null)

    override var message: String? = message
        private set
    var code: ErrorCode = code
        private set
    var detail: Any? = null
        private set

    // 프론트에게 보여지는 오류 (프론트가 사용)
    class CustomError(
        message: String?,
        code: String,
        detail: Any?
    ) {
        var message: String? = message
            private set
        var code: String = code
            private set
        var detail: Any? = detail
            private set
    }

    fun toCustomError() = CustomError(
        message = message,
        code = code.code, // ex) "E0001"
        detail = detail
    )
}