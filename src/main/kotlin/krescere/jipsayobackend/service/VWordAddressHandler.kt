package krescere.jipsayobackend.service

import com.google.gson.Gson
import com.google.gson.JsonObject
import krescere.jipsayobackend.dto.DealHistory
import org.springframework.stereotype.Service

@Service
class VWordAddressHandler(
    private val httpHandler: ApacheHttpHandler,
    private val gson: Gson
) {
    fun getRoadAddress(dealHistory: DealHistory): String {
        // roadNameCityCode to roadNameCityName
        // if return "" then return ""
        val roadNameCityName = getRoadNameCityName(dealHistory.roadNameCityCode!!)
        if(roadNameCityName == "") return ""
        // roadNameCityName +" "+ roadNameBuildingMainCode +"_"+ roadNameBuildingSubCode
        return if(dealHistory.roadNameBuildingSubCode == "0") {
            roadNameCityName + " " + dealHistory.roadNameBuildingMainCode
        } else {
            roadNameCityName + " " + dealHistory.roadNameBuildingMainCode + "_" + dealHistory.roadNameBuildingSubCode
        }
    }

    private fun getRoadNameCityName(roadNameCityCode: String): String {
        val baseURL = "http://api.vworld.kr/req/data"
        val service = "data"
        val request = "getFeature"
        val data = "LT_C_ADSIGG_INFO"
        val key = "8425B03A-0112-30A8-AD77-74D7F5FD6C3A"
        val domain = "jipsayo.com"
        val attrFilter = "sig_cd:=:$roadNameCityCode"

        val urlBuilder = StringBuilder()
        urlBuilder.append(baseURL)
        urlBuilder.append("?service=$service")
        urlBuilder.append("&request=$request")
        urlBuilder.append("&data=$data")
        urlBuilder.append("&key=$key")
        urlBuilder.append("&domain=$domain")
        urlBuilder.append("&attrFilter=$attrFilter")

        // if error return ""
        try {
            val response = httpHandler.get(urlBuilder.toString()) ?: throw Exception("response is null")
            val jsonObject = gson.fromJson(response, JsonObject::class.java)
            return jsonObject.get("response")
                .asJsonObject.get("result")
                .asJsonObject.get("featureCollection")
                .asJsonObject.get("features")
                .asJsonArray.get(0)
                .asJsonObject.get("properties")
                .asJsonObject.get("full_nm").asString
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}