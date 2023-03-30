package krescere.jipsayobackend.common.handler

import com.amazonaws.services.s3.AmazonS3Client
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class S3Handler(
    private val s3Client: AmazonS3Client,
) {
    @Value("\${cloud.aws.s3.bucket}")
    var bucket : String? = null

    @Transactional(readOnly = true)
    fun getObjects() : List<String> {
        return s3Client.listObjects(bucket)
            .objectSummaries.map { it.key }
    }

    @Transactional(readOnly = true)
    fun getObjectUrl(key: String) : String {
        return s3Client.getUrl(bucket, key).toString()
    }
}