package krescere.jipsayobackend.common.error

// 서비스상 오류가 발생했을때 사용 (백엔드가 사용)
class CustomException (
    message: String?, // 한글로 작성하는 오류 메시지
    code: ErrorCode, // 프론트가 식별자로 사용할 오류 코드
): RuntimeException() {
    override var message: String? = message
        private set
    var code: ErrorCode = code
        private set
    private var stackTrace: Array<StackTraceElement> = super.getStackTrace()

    // 프론트에게 보여지는 오류 (프론트가 사용)
    class CustomError(
        message: String?,
        code: String,
        stackTrace: Array<StackTraceElement>
    ) {
        var message: String? = message
            private set
        var code: String = code
            private set
        var stackTrace: Array<StackTraceElement> = stackTrace
            private set
    }

    fun toCustomError() = CustomError(
        message = message,
        code = code.code, // ex) "E0001"
        stackTrace = stackTrace
    )
}