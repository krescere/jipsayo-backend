package krescere.jipsayobackend.common.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val code: String, // 프론트가 식별자로 사용할 오류 코드
    val status: HttpStatus, // 오류 발생시 반환할 HTTP 상태 코드
    val message: String // 한글로 작성하는 오류 메시지
) {
    // House
    HOUSE_NOT_FOUND("H001", HttpStatus.BAD_REQUEST, "부동산을 찾을 수 없습니다."),
    DUPLICATE_HOUSE("H002", HttpStatus.BAD_REQUEST, "중복된 부동산입니다."),
    DUPLICATE_JIBUNADDRESS("H003", HttpStatus.BAD_REQUEST, "중복된 지번주소입니다."),
    DUPLICATE_ROADADDRESS("H004", HttpStatus.BAD_REQUEST, "중복된 도로명주소입니다."),
    // Research

    // Server
    INTERNAL_SERVER_ERROR("S001", HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error")
}