package krescere.jipsayobackend.controller

import krescere.jipsayobackend.common.CustomBody
import krescere.jipsayobackend.common.CustomResponse
import krescere.jipsayobackend.service.DealService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@RestController
class DealController(
    private val dealService: DealService
) {
    val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!

    @GetMapping("/deals")
    fun getDeals() : ResponseEntity<CustomBody>{
        return CustomResponse(
            status = HttpStatus.OK,
            CustomBody(
                message = "거래 조회 성공",
                data = dealService.getDeals()
            )
        ).toResponseEntity().also {
            logger.info("get deals")
        }
    }
}