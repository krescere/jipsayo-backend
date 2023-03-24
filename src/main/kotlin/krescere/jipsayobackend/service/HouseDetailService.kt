package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.houseDetail.HouseDetailGetRequest
import krescere.jipsayobackend.dto.houseDetail.HouseDetailSaveRequest
import krescere.jipsayobackend.entity.HouseDetail
import krescere.jipsayobackend.repository.HouseDetailRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HouseDetailService(
    private val houseDetailRepository: HouseDetailRepository,
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
    fun findHouseDetailOrSave(request: HouseDetailGetRequest): HouseDetail {
        return houseDetailRepository.findByDedicatedAreaAndHouse(
            request.dedicatedArea,
            request.house
        ) ?: houseDetailRepository.save(
            HouseDetail(
                dedicatedArea = request.dedicatedArea,
                house = request.house
            )
        )
    }
}