package krescere.jipsayobackend.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import krescere.jipsayobackend.common.YamlPropertySourceFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource(value = ["classpath:application-s3.yml"], factory = YamlPropertySourceFactory::class)
class S3Config(
    @Value("\${cloud.aws.credentials.access-key}")
    val accessKey : String,
    @Value("\${cloud.aws.credentials.secret-key}")
    val secretKey : String,
    @Value("\${cloud.aws.region.static}")
    val region : String
) {
    @Bean
    fun s3Client() : AmazonS3Client {
        return AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .withRegion(region)
            .build() as AmazonS3Client
    }
}