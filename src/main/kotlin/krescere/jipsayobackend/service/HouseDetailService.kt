package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.HouseDetailDto
import krescere.jipsayobackend.entity.HouseDetail
import krescere.jipsayobackend.repository.HouseDetailRepository
import org.springframework.stereotype.Service

@Service
class HouseDetailService(
    private val houseDetailRepository: HouseDetailRepository,
) {
    // save
    fun save(houseDetailDto: HouseDetailDto) {
        // db에 저장한다.
        houseDetailRepository.save(HouseDetail(
            dedicatedArea = houseDetailDto.dedicatedArea,
            house = houseDetailDto.house
        ))
    }
}