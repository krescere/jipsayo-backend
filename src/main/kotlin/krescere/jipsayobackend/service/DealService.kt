package krescere.jipsayobackend.service

import org.apache.http.HttpEntity
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.util.EntityUtils
import org.springframework.stereotype.Service

@Service
class DealService(
    private val httpClient: CloseableHttpClient
) {
    val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!
    fun getDeals() : String? {
        val url="http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?"
        // params
        val serviceKey="1JBdQZMCAkRsMQpdbldQ0lc3NU%2BRweuoWT4V2bJQrYrBM6TM%2Fpm3c9R8U878%2FoFoV8c521rVU1xQMqS4kKQs7w%3D%3D"
/*
        # 재근
        service_key = "1JBdQZMCAkRsMQpdbldQ0lc3NU%2BRweuoWT4V2bJQrYrBM6TM%2Fpm3c9R8U878%2FoFoV8c521rVU1xQMqS4kKQs7w%3D%3D"
        # 최현호 ##
        #service_key = "mgb1DwH8t0Aji0u29ZP1gp3zz6PSbAYMIYQYoROQCDA86QD5ZZaGlW3QfYUF6i2EUwORd0mQv1mkU%2FZugrNEAQ%3D%3D"
        # 경민
        #service_key = "sRAhCwHRAK0cXEtlrLlUhNXY3%2F4NKDqQ70Utau8CGVch2sCqX54v1dk4lF1WguTUI%2F5TmfZncsWa%2FevT0oraWg%3D%3D"
        # 서진환
        #service_key = "4ddPArOMJR8f2%2B%2BmviXWRHcFztq4H6wAO5mKUD5vnxOMIyqycRRKSOl%2F0S%2BzNkRzFRS41X6F5rktbmFOQSvLIQ%3D%3D"
*/
        val pageNo=1
        val numOfRows=10
        val LAWD_CD=11110
        val DEAL_YMD=201512

        val get= HttpGet(url+
        "serviceKey=$serviceKey"+
                "&pageNo=$pageNo"+
                "&numOfRows=$numOfRows"+
                "&LAWD_CD=$LAWD_CD"+
                "&DEAL_YMD=$DEAL_YMD")
        get.setHeader("Content-type", "application/json; charset=utf-8")

        // timeout 10 seconds
        val TEN_SEC=10000
        val config= RequestConfig.custom()
            .setConnectTimeout(TEN_SEC)
            .setConnectionRequestTimeout(TEN_SEC)
            .setSocketTimeout(TEN_SEC)
            .build()
        get.config=config

        var response: CloseableHttpResponse? = null
        var entity: HttpEntity? = null
        var responseBody: String? = null
        try {
            response = httpClient.execute(get)
            entity = response.entity
            responseBody = EntityUtils.toString(entity, "UTF-8")
            logger.info(responseBody)
        } catch (e: Exception) {
            logger.error(e.message)
        } finally {
            response?.close()
            get.releaseConnection()
        }
        return responseBody
    }

    fun hello() : String{
        return "hello hyunho"
    }
}