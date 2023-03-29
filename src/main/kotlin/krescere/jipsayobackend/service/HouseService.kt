package krescere.jipsayobackend.service

import com.google.gson.Gson
import krescere.jipsayobackend.common.error.CustomException
import krescere.jipsayobackend.common.error.ErrorCode
import krescere.jipsayobackend.dto.*
import krescere.jipsayobackend.dto.dealHistory.DealHistoryGetRequest
import krescere.jipsayobackend.dto.house.HouseGetRequest
import krescere.jipsayobackend.dto.house.HouseGetResponse
import krescere.jipsayobackend.dto.house.HouseSaveRequest
import krescere.jipsayobackend.dto.dealHistory.LawDealHistory
import krescere.jipsayobackend.entity.House
import krescere.jipsayobackend.repository.HouseRepository
import krescere.jipsayobackend.service.handler.AddressHandler
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
    private val addressHandler: AddressHandler,
) {
    var logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!

    @Transactional
    fun save(request: HouseSaveRequest) : Long {
        // check duplicate
        houseRepository.findByRoadAddressAndDanjiName(request.roadAddress, request.danjiName)?.let {
            throw CustomException(ErrorCode.DUPLICATE_ROAD_ADDRESS_AND_DANJI_NAME)
        }
        return houseRepository.save(House(
            jibunAddress = request.jibunAddress,
            roadAddress = request.roadAddress,
            hangCode = request.hangCode,
            danjiName = request.danjiName,
            postCode = request.postCode,
            latitude = request.latitude,
            longitude = request.longitude
        )).id!!
    }

    @Transactional(readOnly = true)
    fun find(request: HouseGetRequest) : HouseGetResponse? {
        val house =
            if(request.roadAddress!=null && request.danjiName!=null)
                houseRepository.findByRoadAddressAndDanjiName(request.roadAddress, request.danjiName)
            else
                request.id?.let { houseRepository.findById(it).orElse(null) }
        return house?.let { HouseGetResponse(it) }
    }

    @Transactional(readOnly = true)
    fun get(request: HouseGetRequest) : House? {
        return if(request.roadAddress!=null && request.danjiName!=null)
            houseRepository.findByRoadAddressAndDanjiName(request.roadAddress, request.danjiName)
        else
            request.id?.let { houseRepository.findById(it).orElse(null) }
    }

    @Transactional
    fun deleteByQuery(request : HouseGetRequest) {
        val house =
            if(request.roadAddress!=null && request.danjiName!=null)
                houseRepository.findByRoadAddressAndDanjiName(request.roadAddress, request.danjiName)
            else
                request.id?.let { houseRepository.findById(it).orElse(null) }
        if(house==null) throw CustomException(ErrorCode.HOUSE_NOT_FOUND)
        houseRepository.delete(house)
    }

    @Transactional
    fun findHouseOrSave(lawDealHistory: LawDealHistory): House {
        // get house save request
        val houseSaveRequest = addressHandler.getHouseSaveRequest(lawDealHistory)

        return houseRepository.findByRoadAddressAndDanjiName(houseSaveRequest.roadAddress, houseSaveRequest.danjiName)
            ?: houseRepository.save(House(
                jibunAddress = houseSaveRequest.jibunAddress,
                roadAddress = houseSaveRequest.roadAddress,
                hangCode = houseSaveRequest.hangCode,
                danjiName = houseSaveRequest.danjiName,
                postCode = houseSaveRequest.postCode,
                latitude = houseSaveRequest.latitude,
                longitude = houseSaveRequest.longitude,
            ))
    }
}