package krescere.jipsayobackend.controller

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonValue
import krescere.jipsayobackend.dto.HouseSaveRequest
import krescere.jipsayobackend.service.HouseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1")
@RestController
class HouseController(
    private val houseService: HouseService
) {
    @PostMapping("/houses")
    fun save(@RequestBody houseSaveRequest: HouseSaveRequest) : ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(houseService.save(houseSaveRequest))
    }

    @GetMapping("/houses/{jibunAddress}")
    fun findByJibunAddress(@PathVariable jibunAddress: String) : ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(houseService.findByJibunAddress(jibunAddress))
    }
}