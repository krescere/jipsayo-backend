package krescere.jipsayobackend.service

import org.springframework.stereotype.Service

@Service
class ImageService(
    private val s3Handler: S3Handler
){
    fun getAgeImages(): List<String> {
        // filter img & sort
        return s3Handler.getObjects()
            .filter { it.contains(".jpg") }
            .sortedBy { it.substringBefore(".").substringAfter("/").toInt() }
            .map { s3Handler.getObjectUrl(it) }
    }
}