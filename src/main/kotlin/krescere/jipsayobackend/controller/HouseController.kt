package krescere.jipsayobackend.controller

import krescere.jipsayobackend.common.CustomBody
import krescere.jipsayobackend.common.CustomResponse
import krescere.jipsayobackend.dto.*
import krescere.jipsayobackend.dto.common.EntitySaveResponse
import krescere.jipsayobackend.dto.house.HouseGetRequest
import krescere.jipsayobackend.dto.house.HouseSaveRequest
import krescere.jipsayobackend.service.HouseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/api/v1")
@RestController
class HouseController(
    private val houseService: HouseService
) {
    val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!
    @PostMapping("/houses")
    fun save(@RequestBody request: HouseSaveRequest) : ResponseEntity<CustomBody> {
        return CustomResponse(
            status = HttpStatus.CREATED,
            CustomBody(
                message = "부동산 저장 성공",
                data = EntitySaveResponse(id = houseService.save(request))
            )
        ).toResponseEntity().also {
            logger.info("save house: $request")
        }
    }

    @GetMapping("/houses")
    fun find(request: HouseGetRequest) : ResponseEntity<CustomBody> {
        return CustomResponse(
            status = HttpStatus.OK,
            CustomBody(
                message = "부동산 조회 성공",
                data = houseService.find(request)
                    ?: Collections.emptyMap<String, Any>()
            )
        ).toResponseEntity().also {
            logger.info("get house: $request")
        }
    }

    @DeleteMapping("/houses")
    fun delete(request: HouseGetRequest) : ResponseEntity<CustomBody> {
        houseService.deleteByQuery(request)
        return CustomResponse(
            status = HttpStatus.OK,
            CustomBody(
                message = "부동산 삭제 성공"
            )
        ).toResponseEntity().also {
            logger.info("delete house: $request")
        }
    }
}