package krescere.jipsayobackend.service

import com.google.gson.Gson
import krescere.jipsayobackend.common.error.CustomException
import krescere.jipsayobackend.common.error.ErrorCode
import krescere.jipsayobackend.dto.*
import krescere.jipsayobackend.entity.House
import krescere.jipsayobackend.repository.HouseRepository
import org.apache.http.HttpEntity
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.util.EntityUtils
import org.locationtech.jts.geom.Point
import org.locationtech.jts.io.WKTReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.nio.charset.StandardCharsets


@Service
class HouseService(
    private val houseRepository: HouseRepository,
    private val wktReader: WKTReader,
    private val httpClient: CloseableHttpClient,
    private val gson: Gson
) {
    val logger=org.slf4j.LoggerFactory.getLogger(HouseService::class.java)
    fun toPoint(latitude: String, longitude: String) : Point {
        return wktReader.read("POINT($longitude $latitude)") as Point
    }

    @Transactional
    fun save(request: HouseSaveRequest) : Long {
        // check duplicate
        houseRepository.findByRoadAddressAndDanjiName(request.roadAddress, request.danjiName)?.let {
            throw CustomException(ErrorCode.DUPLICATE_ROAD_ADDRESS_AND_DANJI_NAME)
        }
        return houseRepository.save(House(
            jibunAddress = request.jibunAddress,
            roadAddress = request.roadAddress,
            cost = request.cost,
            hangCode = request.hangCode,
            danjiName = request.danjiName,
            postCode = request.postCode,
            location = toPoint(request.latitude, request.longitude)
        )).id!!
    }

    @Transactional(readOnly = true)
    fun findByQuery(query: HouseGetQuery) : HouseGetResponse? {
        val house =
            if(query.roadAddress!=null && query.danjiName!=null)
                houseRepository.findByRoadAddressAndDanjiName(query.roadAddress!!, query.danjiName!!)
            else
                query.id?.let { houseRepository.findById(it).orElse(null) }
        return house?.let { HouseGetResponse(it) }
    }

    @Transactional
    fun updateByQuery(query : HouseGetQuery, request: HouseUpdateRequest) {
        val house =
            if(query.roadAddress!=null && query.danjiName!=null)
                houseRepository.findByRoadAddressAndDanjiName(query.roadAddress!!, query.danjiName!!)
            else
                query.id?.let { houseRepository.findById(it).orElse(null) }
        if(house==null) throw CustomException(ErrorCode.HOUSE_NOT_FOUND)

        request.jibunAddress?.let { house.updateJibunAddress(it) }
        request.roadAddress?.let { house.updateRoadAddress(it) }
        request.cost?.let { house.updateCost(it) }
        request.hangCode?.let { house.updateHangCode(it) }
        request.danjiName?.let { house.updateDanjiName(it) }
        request.postCode?.let { house.updatePostCode(it) }
        if(request.latitude!=null && request.longitude!=null) {
            house.updateLocation(toPoint(request.latitude!!, request.longitude!!))
        }
    }

    @Transactional
    fun deleteByQuery(query : HouseGetQuery) {
        val house =
            if(query.roadAddress!=null && query.danjiName!=null)
                houseRepository.findByRoadAddressAndDanjiName(query.roadAddress!!, query.danjiName!!)
            else
                query.id?.let { houseRepository.findById(it).orElse(null) }
        if(house==null) throw CustomException(ErrorCode.HOUSE_NOT_FOUND)
        houseRepository.delete(house)
    }

    // 프론트에서 필터링 요청올 때
    @Transactional(readOnly = true)
    fun filter(request: HouseFilterGetRequest): List<HouseGetResponse> {
        // 예측 모델에 요청
        val candidatesJson=predict(request)
        // responseBody to House
        val houses= mutableListOf<HouseGetResponse>()
        return try{
            val candidateHouses=gson.fromJson(candidatesJson, Array<HousePredictResponse>::class.java).toList()
            for(candidateHouse in candidateHouses){
                val house= houseRepository.findById(candidateHouse.id).orElse(null) ?: continue
                // 시간이 초과되면 패스
                if(candidateHouse.time>request.time) continue
                // 가격이 초과되면 패스
                if(house.cost>request.cost) continue
                houses.add(HouseGetResponse(house))
            }
            // 최대 10개까지만 보냄
            houses.take(10)
        } catch (e: Exception) {
            e.printStackTrace()
            houses
        }
    }

    fun predict(request: HouseFilterGetRequest) : String?{
        val predictServerUrl="http://localhost:5000/api/v1/houses/filter"
        val get=HttpGet(predictServerUrl+
                "?latitude=${request.latitude}&" +
                "longitude=${request.longitude}&" +
                "time=${request.time}")
        get.setHeader("Content-type", "application/json; charset=utf-8")

        // timeout 10 seconds
        val TEN_SEC=10000
        val config=RequestConfig.custom()
            .setConnectTimeout(TEN_SEC)
            .setConnectionRequestTimeout(TEN_SEC)
            .setSocketTimeout(TEN_SEC)
            .build()
        get.config=config

        var response: CloseableHttpResponse? = null
        var entity: HttpEntity? = null
        var responseBody: String? = null
        try{
            response= httpClient.execute(get)
            entity=response.entity
            responseBody=EntityUtils.toString(entity, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            response?.close()
            get.releaseConnection()
        }
        return responseBody
    }
}