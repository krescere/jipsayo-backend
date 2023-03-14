package krescere.jipsayobackend.service

import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.util.EntityUtils
import org.springframework.stereotype.Service

@Service
class ApacheHttpHandler(
    private var httpClient: CloseableHttpClient
) {
    // logger
    var logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!
    fun get(url: String) : String? {
        val httpGet = HttpGet(url)
        httpGet.setHeader("Content-type", "application/json; charset=utf-8")

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
            println(responseBody)
        } catch (e: Exception) {
            logger.error(e.message)
        } finally {
            response?.close()
            httpGet.releaseConnection()
        }
        return responseBody
    }
}