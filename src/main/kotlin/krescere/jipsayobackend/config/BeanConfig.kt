package krescere.jipsayobackend.config

import com.google.gson.Gson
import org.apache.http.client.config.RequestConfig
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.locationtech.jts.io.WKTReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

@Configuration
class BeanConfig {
    @Bean
    fun gson() = Gson()

    @Bean
    fun wktReader() = WKTReader()

    @Bean
    fun httpClient(): CloseableHttpClient? {
        val cm=PoolingHttpClientConnectionManager()
        /**
         * maxTotal : Connection Pool의 수용 가능한 최대 사이즈
         * defaultMaxPerRoute : 각 host(IP와 Port의 조합)당 Connection Pool에 생성가능한 Connection 수
         */
        cm.maxTotal=100
        cm.defaultMaxPerRoute=50
        return HttpClients.custom()
            .setConnectionManager(cm)
            .setDefaultRequestConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(2000)
                .setConnectTimeout(2000)
                .build())
            .build()
    }

    @Bean
    fun saxParserFactory() : SAXParserFactory {
        return SAXParserFactory.newInstance()
    }

    @Bean
    fun saxParser() : SAXParser {
        return saxParserFactory().newSAXParser()
    }
}