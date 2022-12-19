package krescere.jipsayobackend.controller

import com.google.gson.Gson
import com.google.gson.JsonObject
import krescere.jipsayobackend.dto.HouseGetQuery
import krescere.jipsayobackend.dto.HouseSaveRequest
import krescere.jipsayobackend.dto.HouseUpdateRequest
import krescere.jipsayobackend.service.HouseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/api/v1")
@RestController
class HouseController(
    private val houseService: HouseService,
    private val gson: Gson
) {
    @PostMapping("/houses")
    fun save(@RequestBody request: HouseSaveRequest) : ResponseEntity<Any> {
        val jsonObject=JsonObject()
        jsonObject.addProperty("id", houseService.save(request))
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(gson.toJson(jsonObject))
    }

    @GetMapping("/houses")
    fun findByQuery(query: HouseGetQuery) : ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(houseService.findByQuery(query)
                ?: Collections.emptyMap<String, Any>())
    }

    @PutMapping("/houses")
    fun updateByQuery(query: HouseGetQuery, @RequestBody houseUpdateRequest: HouseUpdateRequest) : ResponseEntity<Any> {
        val jsonObject=JsonObject()
        jsonObject.addProperty("message","업데이트 성공")
        houseService.updateByQuery(query, houseUpdateRequest)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(gson.toJson(jsonObject))
    }

    @DeleteMapping("/houses")
    fun deleteByQuery(query: HouseGetQuery) : ResponseEntity<Any> {
        val jsonObject=JsonObject()
        jsonObject.addProperty("message","삭제 성공")
        houseService.deleteByQuery(query)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(gson.toJson(jsonObject))
    }
}