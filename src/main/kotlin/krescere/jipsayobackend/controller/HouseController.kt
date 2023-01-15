package krescere.jipsayobackend.controller

import krescere.jipsayobackend.common.CustomBody
import krescere.jipsayobackend.common.CustomResponse
import krescere.jipsayobackend.dto.*
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
    fun findByQuery(query: HouseGetQuery) : ResponseEntity<CustomBody> {
        return CustomResponse(
            status = HttpStatus.OK,
            CustomBody(
                message = "부동산 조회 성공",
                data = houseService.findByQuery(query)
                    ?: Collections.emptyMap<String, Any>()
            )
        ).toResponseEntity().also {
            logger.info("get house: $query")
        }
    }

    @PutMapping("/houses")
    fun updateByQuery(query: HouseGetQuery, @RequestBody houseUpdateRequest: HouseUpdateRequest) : ResponseEntity<CustomBody> {
        houseService.updateByQuery(query, houseUpdateRequest)
        return CustomResponse(
            status = HttpStatus.OK,
            CustomBody(
                message = "부동산 수정 성공"
            )
        ).toResponseEntity().also {
            logger.info("update house: $query, $houseUpdateRequest")
        }
    }

    @DeleteMapping("/houses")
    fun deleteByQuery(query: HouseGetQuery) : ResponseEntity<CustomBody> {
        houseService.deleteByQuery(query)
        return CustomResponse(
            status = HttpStatus.OK,
            CustomBody(
                message = "부동산 삭제 성공"
            )
        ).toResponseEntity().also {
            logger.info("delete house: $query")
        }
    }

    // 프론트에서 필터링 요청올 때
    @GetMapping("/houses/filter")
    fun filterByQuery(request: HouseFilterGetRequest) : ResponseEntity<CustomBody> {
        return CustomResponse(
            status = HttpStatus.OK,
            CustomBody(
                message = "부동산 필터링 조회 성공",
                data = houseService.filter(request)
            )
        ).toResponseEntity().also {
            logger.info("filter house: $request")
        }
    }
}