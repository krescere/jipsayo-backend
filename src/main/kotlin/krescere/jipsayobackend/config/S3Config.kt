package krescere.jipsayobackend.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.*
import java.util.*

@Configuration
@PropertySources(
    PropertySource("classpath:application-s3.properties", ignoreResourceNotFound = true),
    PropertySource("file:/home/ec2-user/app/application-s3.properties", ignoreResourceNotFound = true)
)
class S3Config {
    @Value("\${cloud.aws.credentials.access-key}")
    lateinit var accessKey: String
    @Value("\${cloud.aws.credentials.secret-key}")
    lateinit var secretKey: String
    @Value("\${cloud.aws.region.static}")
    lateinit var region: String

    @Bean
    fun s3Client(): AmazonS3Client {
        return AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .withRegion(region)
            .build() as AmazonS3Client
    }

}
