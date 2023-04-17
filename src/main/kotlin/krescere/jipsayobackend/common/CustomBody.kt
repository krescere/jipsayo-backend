package krescere.jipsayobackend.common

import krescere.jipsayobackend.common.error.CustomException
import krescere.jipsayobackend.common.error.GlobalExceptionHandler
import java.time.LocalDateTime

class CustomBody(
    data: Any? = null, // 성공시 반환할 데이터
    message: String? = null, // 성공시 반환할 메시지
    timestamp: LocalDateTime = LocalDateTime.now(), // 요청 시간
    errors: List<GlobalExceptionHandler.Error>? = null // 오류 발생시 반환할 오류 목록
) {
    var data: Any? = data
        private set
    var message: String? = message
        private set
    var timestamp: LocalDateTime = timestamp
        private set
    var errors: List<GlobalExceptionHandler.Error>? = errors
        private set
}