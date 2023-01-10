package krescere.jipsayobackend.config

import com.google.gson.Gson
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {
    @Bean
    fun gson() = Gson()

    @Bean
    fun wktReader() = org.locationtech.jts.io.WKTReader()
}