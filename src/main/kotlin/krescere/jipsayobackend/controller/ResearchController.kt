package krescere.jipsayobackend.controller

import krescere.jipsayobackend.dto.ResearchSaveRequest
import krescere.jipsayobackend.service.ResearchService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Objects

@RequestMapping("/api/v1")
@RestController
class ResearchController(
    private val researchService: ResearchService
){
    @PostMapping("/research")
    fun save(@RequestBody researchSaveRequest: ResearchSaveRequest) : ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(researchService.save(researchSaveRequest))
    }
}
