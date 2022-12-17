package krescere.jipsayobackend.controller

import krescere.jipsayobackend.dto.HouseSaveRequest
import krescere.jipsayobackend.service.HouseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}