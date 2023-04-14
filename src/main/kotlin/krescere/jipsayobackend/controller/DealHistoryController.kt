package krescere.jipsayobackend.controller

import krescere.jipsayobackend.common.CustomBody
import krescere.jipsayobackend.common.CustomResponse
import krescere.jipsayobackend.dto.dealHistory.DealHistoryFilterRequest
import krescere.jipsayobackend.dto.dealHistory.DealHistoryGetRequest
import krescere.jipsayobackend.dto.dealHistory.DealHistorySaveRequest
import krescere.jipsayobackend.service.DealHistoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1")
@RestController
class DealHistoryController(
    private val dealHistoryService: DealHistoryService
) {
    val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!

    @PostMapping("/dealHistories")
    fun save(@RequestBody request: DealHistorySaveRequest) : ResponseEntity<CustomBody> {
        dealHistoryService.save(request)
        return CustomResponse(
            status = HttpStatus.CREATED,
            CustomBody(
                message = "거래내역 저장 성공"
            )
        ).toResponseEntity().also {
            logger.info("save dealHistory: $request")
        }
    }

    @GetMapping("/dealHistories")
    fun find(request: DealHistoryGetRequest) : ResponseEntity<CustomBody> {
        return CustomResponse(
            status = HttpStatus.OK,
            CustomBody(
                message = "거래내역 조회 성공",
                data = dealHistoryService.find(request)
            )
        ).toResponseEntity().also {
            logger.info("find dealHistory: $request")
        }
    }

    @GetMapping("/dealHistories/filter")
    fun filter(request: DealHistoryFilterRequest) : ResponseEntity<CustomBody> {
        return CustomResponse(
            status = HttpStatus.OK,
            CustomBody(
                message = "거래내역 필터링 성공",
                data = dealHistoryService.filter(request)
            )
        ).toResponseEntity().also {
            logger.info("filter dealHistory: $request")
        }
    }

    @GetMapping("/dealHistories/filter/reload")
    fun filterReload() : ResponseEntity<CustomBody> {
        dealHistoryService.filterReload()
        return CustomResponse(
            status = HttpStatus.OK,
            CustomBody(
                message = "데이터 리로딩 성공",
            )
        ).toResponseEntity().also {
            logger.info("filter dealHistory: reload")
        }
    }
}