package krescere.jipsayobackend.config

import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class WebConfig : WebMvcConfigurer{
    // cors
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // TODO : 운영 단계에서는 프론트 서버만
                .allowedMethods("*")
    }
}