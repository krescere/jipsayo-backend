package krescere.jipsayobackend.common.handler

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import krescere.jipsayobackend.dto.common.KakaoAddressResponse
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.net.URLEncoder
import java.net.http.HttpHeaders
import java.nio.charset.StandardCharsets

@Component
class KakaoAddressHandler(
    private val httpHandler: ApacheHttpHandler,
    private val gson: Gson
) {
    // roadAddress search
    fun search(roadAddress: String): KakaoAddressResponse {
        val urlBuilder = StringBuilder()
        val baseURL="https://dapi.kakao.com/v2/local/search/address.json?"
        val query=URLEncoder.encode(roadAddress,StandardCharsets.UTF_8.toString())
        // header에 api key 추가

        urlBuilder.append(baseURL)
        urlBuilder.append("query=${query}")

        // add rest_api_key
        val headers = mutableMapOf<String,String>()
        val restApiKey="2aa3b0e83391bf1ae258957941ef8564"
        headers.put("Authorization", "KakaoAK $restApiKey")

        val response = httpHandler.get(urlBuilder.toString(),headers)
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
        return documents[0].asJsonObject.get("road_address").asJsonObject.get("building_name").asString
    }

    private fun getPostCode(documents: JsonArray): Int {
        return documents[0].asJsonObject.get("road_address").asJsonObject.get("zone_no").asInt
    }

    private fun getLatitude(documents: JsonArray): BigDecimal {
        return documents[0].asJsonObject.get("y").asBigDecimal
    }

    private fun getLongitude(documents: JsonArray): BigDecimal {
        return documents[0].asJsonObject.get("x").asBigDecimal
    }
}