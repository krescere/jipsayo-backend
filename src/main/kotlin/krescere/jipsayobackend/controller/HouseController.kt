package krescere.jipsayobackend.controller

import com.google.gson.Gson
import com.google.gson.JsonObject
import krescere.jipsayobackend.dto.HouseSaveRequest
import krescere.jipsayobackend.dto.HouseUpdateRequest
import krescere.jipsayobackend.service.HouseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1")
@RestController
class HouseController(
    private val houseService: HouseService,
    private val gson: Gson
) {
    @PostMapping("/houses")
    fun save(@RequestBody houseSaveRequest: HouseSaveRequest) : ResponseEntity<Any> {
        val jsonObject=JsonObject()
        jsonObject.addProperty("id", houseService.save(houseSaveRequest))
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(gson.toJson(jsonObject))
    }

    @GetMapping("/houses/{jibunAddress}")
    fun findByJibunAddress(@PathVariable jibunAddress: String) : ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(houseService.findByJibunAddress(jibunAddress))
    }

    @PutMapping("/houses/{jibunAddress}")
    fun updateByJibunAddress(@PathVariable jibunAddress: String, @RequestBody houseUpdateRequest: HouseUpdateRequest) : ResponseEntity<Any> {
        houseService.updateByJibunAddress(jibunAddress, houseUpdateRequest)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null)
    }
}