package krescere.jipsayobackend.service

import krescere.jipsayobackend.common.error.CustomException
import krescere.jipsayobackend.common.error.ErrorCode
import krescere.jipsayobackend.dto.house.HouseGetRequest
import krescere.jipsayobackend.dto.houseDetail.HouseDetailGetRequest
import krescere.jipsayobackend.dto.houseDetail.HouseDetailSaveRequest
import krescere.jipsayobackend.entity.HouseDetail
import krescere.jipsayobackend.repository.HouseDetailRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HouseDetailService(
    private val houseDetailRepository: HouseDetailRepository,
    private val houseService: HouseService
) {
    // save
    @Transactional
    fun save(request: HouseDetailSaveRequest) : Long {
        // db에 저장한다.
        return houseDetailRepository.save(HouseDetail(
            dedicatedArea = request.dedicatedArea,
            house = request.house
        )).id!!
    }

    @Transactional
    fun getHouseDetailOrSave(request: HouseDetailGetRequest): HouseDetail {
        val house = houseService.get(HouseGetRequest(id = request.houseId))
            ?: throw Exception("house not found")
        // validation
        if(request.dedicatedArea == null) throw CustomException(ErrorCode.INVALID_INPUT_VALUE, "dedicatedArea is null")

        return houseDetailRepository.findByDedicatedAreaAndHouse(
            dedicatedArea = request.dedicatedArea,
            house = house
        ) ?: houseDetailRepository.save(HouseDetail(
                dedicatedArea = request.dedicatedArea,
                house = house
            )
        )
    }

    @Transactional
    fun raiseCount(request: HouseDetailGetRequest) {
        val houseDetail = get(request)
            ?: throw CustomException(ErrorCode.HOUSE_DETAIL_NOT_FOUND)
        houseDetail.raiseCount()
    }

    @Transactional(readOnly = true)
    fun get(request: HouseDetailGetRequest): HouseDetail? {
        return request.id?.let { houseDetailRepository.findById(it).orElse(null) }
    }
}