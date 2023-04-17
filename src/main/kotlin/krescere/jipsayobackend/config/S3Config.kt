package krescere.jipsayobackend.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:application-s3.yml")
class S3Config {
    @Value("\${cloud.aws.credentials.access-key}")
    var accessKey : String? = null
    @Value("\${cloud.aws.credentials.secret-key}")
    var secretKey : String? = null
    @Value("\${cloud.aws.region.static}")
    var region : String? = null

    @Bean
    fun s3Client() : AmazonS3Client {
        return AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .withRegion(region)
            .build() as AmazonS3Client
    }
}