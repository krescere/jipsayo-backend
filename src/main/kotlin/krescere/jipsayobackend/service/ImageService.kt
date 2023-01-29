package krescere.jipsayobackend.service

import org.springframework.stereotype.Service

@Service
class ImageService(
    private val s3Handler: S3Handler
){
    fun getAgeImages() : List<String> {
        val images = s3Handler.getObjects()
            .map { s3Handler.getObjectUrl(it) }
        return images.subList(1, images.size)
    }
}