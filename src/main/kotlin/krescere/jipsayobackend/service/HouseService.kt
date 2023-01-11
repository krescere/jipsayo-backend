package krescere.jipsayobackend.service

import krescere.jipsayobackend.common.error.CustomException
import krescere.jipsayobackend.common.error.ErrorCode
import krescere.jipsayobackend.dto.*
import krescere.jipsayobackend.entity.House
import krescere.jipsayobackend.repository.HouseRepository
import org.locationtech.jts.geom.Point
import org.locationtech.jts.io.WKTReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class HouseService(
    private val houseRepository: HouseRepository,
    private val wktReader: WKTReader
) {
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

    @Transactional(readOnly = true)
    fun findAll() : List<HouseGetAllResponse> {
        return houseRepository.findAll().map { HouseGetAllResponse(it) }
    }
}