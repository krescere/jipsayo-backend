package krescere.jipsayobackend.common.handler

import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.util.EntityUtils
import org.springframework.stereotype.Component

@Component
class ApacheHttpHandler(
    private var httpClient: CloseableHttpClient
) {
    // logger
    var logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!
    fun get(url: String,headers: Map<String,String> = emptyMap()) : String? {
        val httpGet = HttpGet(url)
        httpGet.setHeader("Content-type", "application/json; charset=utf-8")

        headers.forEach { (key,value) ->
            httpGet.setHeader(key,value)
        }

        val requestConfig = org.apache.http.client.config.RequestConfig.custom()
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000)
            .setSocketTimeout(5000)
            .build()
        httpGet.config = requestConfig

        var response: CloseableHttpResponse? = null
        var responseBody: String? = null
        try {
            response = httpClient.execute(httpGet)
            val entity = response?.entity
            responseBody = EntityUtils.toString(entity, "UTF-8")
        } catch (e: Exception) {
            logger.error(e.message)
        } finally {
            response?.close()
            httpGet.releaseConnection()
        }
        return responseBody
    }
}