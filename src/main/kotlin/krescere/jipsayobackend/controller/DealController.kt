package krescere.jipsayobackend.controller

import krescere.jipsayobackend.common.CustomBody
import krescere.jipsayobackend.common.CustomResponse
import krescere.jipsayobackend.dto.deal.DealGetRequest
import krescere.jipsayobackend.service.DealService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RequestMapping("/api/v1")
@RestController
class DealController(
    private val dealService: DealService
) {
    val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!

    /**
     * 거래 내역 도로명주소+단지명으로 조회
     * 공통 정보
     * 가격, 면적, 날짜 등 리스트
     * 조회수
     */
    @GetMapping("/deals")
    fun findByQuery(request: DealGetRequest) : ResponseEntity<CustomBody> {
        return CustomResponse(
            status = HttpStatus.OK,
            CustomBody(
                message = "거래 내역 조회 성공",
                data = dealService.find(request)
                    ?: Collections.emptyMap<String, Any>()
            )
        ).toResponseEntity().also {
            logger.info("get deal: $request")
        }
    }
}