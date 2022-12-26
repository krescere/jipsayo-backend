package krescere.jipsayobackend.controller

import com.google.gson.Gson
import com.google.gson.JsonObject
import krescere.jipsayobackend.common.CustomBody
import krescere.jipsayobackend.dto.EntitySaveResponse
import krescere.jipsayobackend.dto.ResearchSaveRequest
import krescere.jipsayobackend.service.ResearchService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@RestController
class ResearchController(
    private val researchService: ResearchService
){
    @PostMapping("/research")
    fun save(@RequestBody researchSaveRequest: ResearchSaveRequest) : ResponseEntity<CustomBody> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(CustomBody(
                message = "설문조사 저장 성공",
                data = EntitySaveResponse(id = researchService.save(researchSaveRequest))
            ))
    }
}
