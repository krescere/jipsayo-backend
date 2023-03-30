package krescere.jipsayobackend.common.handler

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import krescere.jipsayobackend.dto.common.KakaoAddressResponse
import org.springframework.stereotype.Component

@Component
class KakaoAddressHandler(
    private val httpHandler: ApacheHttpHandler,
    private val gson: Gson
) {
    // roadAddress search
    fun search(roadAddress: String): KakaoAddressResponse {
        val urlBuilder = StringBuilder()
        val baseURL="https://dapi.kakao.com/v2/local/search/address.json"
        val query="query=$roadAddress"

        urlBuilder.append(baseURL)
        urlBuilder.append("?query=$query")

        val response = httpHandler.get(urlBuilder.toString())
        val jsonObject = gson.fromJson(response, JsonObject::class.java)
        val documents = jsonObject.get("documents").asJsonArray

        return KakaoAddressResponse(
            jibunAddress = getJibunAddress(documents),
            roadAddress = getRoadAddress(documents),
            hangCode = getHandCode(documents),
            danjiName = getDanjiName(documents),
            postCode = getPostCode(documents),
            latitude = getLatitude(documents),
            longitude = getLongitude(documents),
        )
    }

    private fun getRoadAddress(documents: JsonArray): String {
        return documents[0].asJsonObject.get("road_address").asJsonObject.get("address_name").asString
    }

    private fun getJibunAddress(documents: JsonArray): String {
        return documents[0].asJsonObject.get("address").asJsonObject.get("address_name").asString
    }

    private fun getHandCode(documents: JsonArray): Long {
        return documents[0].asJsonObject.get("address").asJsonObject.get("b_code").asLong
    }

    private fun getDanjiName(documents: JsonArray): String {
        return documents[0].asJsonObject.get("roadAddress").asJsonObject.get("building_name").asString
    }

    private fun getPostCode(documents: JsonArray): Int {
        return documents[0].asJsonObject.get("roadAddress").asJsonObject.get("zone_no").asInt
    }

    private fun getLatitude(documents: JsonArray): Double {
        return documents[0].asJsonObject.get("y").asDouble
    }

    private fun getLongitude(documents: JsonArray): Double {
        return documents[0].asJsonObject.get("x").asDouble
    }
}