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
        val candidatesJson=predict(request)
        // 후보들중 예상 이동시간이 오래걸리고 비싼 집들로 반환
        // order[이동시간 오래걸리는 순, 비싼 순]
        val pq = PriorityQueue<HouseFilterGetResponse> label@{ o1, o2 ->
            if (o1.time == o2.time) return@label (o2.cost - o1.cost).toInt()
            (o2.time - o1.time).toInt()
        }
        return try{
            val candidateList=gson.fromJson(candidatesJson, Array<HousePredictResponse>::class.java).toList()
            val candidateMap= candidateList.associateBy { it.id }

            val streamHouses=houseRepository.streamByCostBefore(request.cost)
            streamHouses.forEach { house ->
                candidateMap[house.id]?.let {
                    pq.add(HouseFilterGetResponse(house, it.time))
                }
                // 메모리에 올라간 Entity를 GC에게 반환
                entityManager?.detach(house)
            }
            // Default 30개 반환
            var count=request.count?:30
            // 최대 갯수 100개
            if(count>100) count=100
            pq.take(count).toList()
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
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