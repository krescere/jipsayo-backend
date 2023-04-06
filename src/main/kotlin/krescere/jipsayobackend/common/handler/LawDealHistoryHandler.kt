package krescere.jipsayobackend.common.handler

import krescere.jipsayobackend.dto.dealHistory.DealHistorySaveRequest
import krescere.jipsayobackend.dto.dealHistory.LawDealHistory
import org.springframework.stereotype.Component
import java.io.File
import javax.xml.parsers.SAXParser

@Component
class LawDealHistoryHandler (
    private val httpHandler: ApacheHttpHandler,
    private val saxParser: SAXParser,
    private val xmlHandler: DealSaxHandler,
){
    fun getLawDealHistories(request: DealHistorySaveRequest) : List<LawDealHistory> {
        val xml = getXml(request)
        // parse xml
        saxParser.parse(xml, xmlHandler)
        // delete temp file
        xml.deleteOnExit()

        return xmlHandler.deals
    }

    fun getTotalCount() = xmlHandler.totalCount

    private fun getXml(request: DealHistorySaveRequest) : File {
        val url="http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?"
        // params
        val serviceKey="sRAhCwHRAK0cXEtlrLlUhNXY3%2F4NKDqQ70Utau8CGVch2sCqX54v1dk4lF1WguTUI%2F5TmfZncsWa%2FevT0oraWg%3D%3D"
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

        val urlBuilder = StringBuilder(url)
        urlBuilder.append("serviceKey=${serviceKey}")
        urlBuilder.append("&pageNo=${request.pageNo}")
        urlBuilder.append("&numOfRows=${request.numOfRows}")
        urlBuilder.append("&LAWD_CD=${request.lawdCd}")
        urlBuilder.append("&DEAL_YMD=${request.dearYmd}")

        val response = httpHandler.get(urlBuilder.toString())
        // string to xml
        val xml = File.createTempFile("deal", ".xml")
        if(response != null) xml.writeText(response)
        return xml
    }
}