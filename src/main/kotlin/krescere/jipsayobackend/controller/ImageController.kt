package krescere.jipsayobackend.controller

import krescere.jipsayobackend.common.CustomBody
import krescere.jipsayobackend.common.CustomResponse
import krescere.jipsayobackend.service.ImageService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RequestMapping("/api/v1")
@RestController
class ImageController(
    private val imageService: ImageService
){
    val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!

    @GetMapping("/images/age")
    fun getAgeImages() : ResponseEntity<CustomBody> {
        return CustomResponse(
            status = HttpStatus.OK,
            CustomBody(
                message = "연령대 이미지 조회 성공",
                data = imageService.getAgeImages()
            )
        ).toResponseEntity().also {
            logger.info("get age images")
        }
    }
}