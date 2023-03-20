package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.DealHistory
import org.springframework.stereotype.Service
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

@Service
class DealSaxHandler : DefaultHandler() {
    val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!

    val deals = ArrayList<DealHistory>()
    var deal : DealHistory? = null
    var value : String? = null
    override fun startDocument() {
    }

    override fun endDocument() {
    }

    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        if(qName == "item") {
            deal = DealHistory()
            deals.add(deal!!)
        }
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        when(qName) {
            "거래금액" -> deal?.cost = value
            "거래유형" -> deal?.dealType = value
            "건축년도" -> deal?.buildYear = value
            "년" -> deal?.dealYear = value
            "도로명" -> deal?.roadName = value
            "도로명건물본번호코드" -> deal?.roadNameBuildingMainCode = value
            "도로명건물부번호코드" -> deal?.roadNameBuildingSubCode = value
            "도로명시군구코드" -> deal?.roadNameCityCode = value
            "도로명일련번호코드" -> deal?.roadNameSerialCode = value
            "도로명지상지하코드" -> deal?.roadNameGroundCode = value
            "도로명코드" -> deal?.roadNameCode = value
            "법정동" -> deal?.legalDong = value
            "법정동본번코드" -> deal?.legalDongMainCode = value
            "법정동부번코드" -> deal?.legalDongSubCode = value
            "법정동시군구코드" -> deal?.legalDongCityCode = value
            "법정동읍면동코드" -> deal?.legalDongEupCode = value
            "법정동지번코드" -> deal?.legalDongJibunCode = value
            "아파트" -> deal?.apartmentName = value
            "월" -> deal?.dealMonth = value
            "일" -> deal?.dealDay = value
            "일련번호" -> deal?.serialCode = value
            "전용면적" -> deal?.dedicatedArea = value
            "중개사소재지" -> deal?.realEstateLocation = value
            "지번" -> deal?.jibun = value
            "지역코드" -> deal?.localCode = value
            "층" -> deal?.floor = value
            "해제사유발생일" -> deal?.cancelDate = value
            "해제여부" -> deal?.isCancel = value
        }
    }

    // between element
    override fun characters(ch: CharArray?, start: Int, length: Int) {
        value = if(ch == null) null
        else String(ch, start, length)
    }
}