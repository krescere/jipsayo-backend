package krescere.jipsayobackend.config

import com.google.gson.Gson
import org.apache.http.impl.client.HttpClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {
    @Bean
    fun gson() = Gson()

    @Bean
    fun wktReader() = org.locationtech.jts.io.WKTReader()

    @Bean
    fun httpClient() = HttpClients.createDefault()!!
}