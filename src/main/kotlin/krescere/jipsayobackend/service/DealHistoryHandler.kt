package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.DealDto
import krescere.jipsayobackend.dto.DealHistory
import krescere.jipsayobackend.dto.DealHistorySaveRequest
import krescere.jipsayobackend.dto.HouseDetailDto
import org.springframework.stereotype.Service
import java.io.File
import javax.xml.parsers.SAXParser

@Service
class DealHistoryHandler(
    private val httpHandler: ApacheHttpHandler,
    private val saxParser: SAXParser,
    private val xmlHandler: DealSaxHandler,
    private val dealService: DealService,
    private val houseService: HouseService,
    private val houseDetailService: HouseDetailService
) {
    // save
    fun save(request: DealHistorySaveRequest) {
        val dealHistories = getDealHistories(request)

        for(dealHistory in dealHistories) {
            // check if dealDto is not null
            if(!isDealDtoNotNull(dealHistory)) {
                continue
            }
            // dealHistory to house
            val house = houseService.getHouseByDealHistory(dealHistory)
            // dealHistory to dealDto
            dealService.save(DealDto(
                cost = dealHistory.cost!!,
                dealYear = dealHistory.dealYear!!,
                dealMonth = dealHistory.dealMonth!!,
                dealDay = dealHistory.dealDay!!,
                house = house,
            ))
            // dealHistory to houseDetail
            houseDetailService.save(HouseDetailDto(
                dedicatedArea = dealHistory.dedicatedArea!!,
                house = house
            ))
        }
    }
    private fun isDealDtoNotNull(dealHistory: DealHistory) : Boolean {
        return dealHistory.cost != null &&
                dealHistory.dealYear != null &&
                dealHistory.dealMonth != null &&
                dealHistory.dealDay != null &&
                dealHistory.dedicatedArea != null &&
                dealHistory.roadNameCityCode != null

    }

    fun getXml(request: DealHistorySaveRequest) : File {
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

        val urlBuilder = StringBuilder(url)
        urlBuilder.append("serviceKey=${serviceKey}")
        urlBuilder.append("&pageNo=${request.pageNo}")
        urlBuilder.append("&numOfRows=${request.numOfRows}")
        urlBuilder.append("&LAWD_CD=${request.LAWD_CD}")
        urlBuilder.append("&DEAL_YMD=${request.DEAL_YMD}")

        val response = httpHandler.get(urlBuilder.toString())
        // string to xml
        val xml = File.createTempFile("deal", ".xml")
        if(response != null) xml.writeText(response)
        return xml
    }

    fun getDealHistories(request: DealHistorySaveRequest) : List<DealHistory> {
        val xml = getXml(request)
        // parse xml
        saxParser.parse(xml, xmlHandler)
        // delete temp file
        xml.deleteOnExit()

        return xmlHandler.deals
    }
}