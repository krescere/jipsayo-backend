package krescere.jipsayobackend.service

import com.google.gson.Gson
import krescere.jipsayobackend.common.error.CustomException
import krescere.jipsayobackend.common.error.ErrorCode
import krescere.jipsayobackend.dto.*
import krescere.jipsayobackend.repository.HouseRepository
import org.apache.http.HttpEntity
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.util.EntityUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.nio.charset.StandardCharsets
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Service
class HouseService(
    private val houseRepository: HouseRepository,
    private val httpClient: CloseableHttpClient,
    private val gson: Gson
) {
    @PersistenceContext
    private val entityManager: EntityManager? = null

    var logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!

    // filter 환경변수
    private val DEFAULT_FILTER_COUNT = 500
    private val MAX_FILTER_COUNT = 500
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
            latitude = request.latitude,
            longitude = request.longitude,
            dealDate = request.dealDate,
            dedicatedArea = request.dedicatedArea,
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
        request.latitude?.let { house.updateLatitude(it) }
        request.longitude?.let { house.updateLongitude(it) }
        request.dealDate?.let { house.updateDealDate(it) }
        request.dedicatedArea?.let { house.updateDedicatedArea(it) }

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
    fun filter(request: HouseFilterGetRequest): List<HouseFilterGetResponse> {
        // 예측 모델에 요청
        val candidatesJson = predict(request) ?: return emptyList()
        // 후보군 리스트
        val ret=ArrayList<HouseFilterGetResponse>()
        val candidateList : List<HousePredictResponse>
        try {
            candidateList=gson.fromJson(candidatesJson, Array<HousePredictResponse>::class.java).toList()
        } catch (e: Exception) {
            logger.error("error in parsing json", e)
            return emptyList()
        }
        val candidateMap= candidateList.associateBy { it.id }

        val streamHouses=houseRepository.streamByCostBeforeAndCostAfter(request.lowCost, request.highCost)
        streamHouses.forEach { house ->
            candidateMap[house.id]?.let {
                ret.add(HouseFilterGetResponse(house, it.time))
            }
            // 메모리에 올라간 Entity를 GC에게 반환
            entityManager?.detach(house)
        }
        // Default 500개 반환
        var count=request.count?:DEFAULT_FILTER_COUNT
        // 최대 갯수 500개
        if(count>MAX_FILTER_COUNT) count=MAX_FILTER_COUNT
        // 시간대 별로 random 하게 셔플
        ret.shuffle()
        return ret.take(count).toList()
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
            logger.error("Error while requesting to predict server", e)
        } finally {
            response?.close()
            get.releaseConnection()
        }
        return responseBody
    }
}