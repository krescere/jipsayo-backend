package krescere.jipsayobackend.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource
import java.util.*

@Configuration
class S3Config {
    lateinit var accessKey: String
    lateinit var secretKey: String
    lateinit var region: String

    @Autowired
    lateinit var environment: Environment

    @Profile("local", "dev")
    private fun setProperties() {
        val properties = Properties()
        val resource = if (isActiveProfile("local")) {
            ClassPathResource("application-s3.properties")
        } else {
            FileSystemResource("/home/ec2-user/app/application-s3.properties")
        }
        properties.load(resource.inputStream)

        accessKey=properties.getProperty("cloud.aws.credentials.access-key")
        secretKey=properties.getProperty("cloud.aws.credentials.secret-key")
        region=properties.getProperty("cloud.aws.region.static")
    }

    @Bean
    fun s3Client(): AmazonS3Client {
        setProperties()
        return AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .withRegion(region)
            .build() as AmazonS3Client
    }
    private fun isActiveProfile(profile: String): Boolean {
        return Arrays.stream(environment.activeProfiles).anyMatch { p -> p == profile }
    }
}
