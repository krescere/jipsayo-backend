package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.HouseGetQuery
import krescere.jipsayobackend.dto.HouseGetResponse
import krescere.jipsayobackend.dto.HouseSaveRequest
import krescere.jipsayobackend.dto.HouseUpdateRequest
import krescere.jipsayobackend.entity.House
import krescere.jipsayobackend.repository.HouseRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HouseService(
    private val houseRepository: HouseRepository
) {
    @Transactional
    fun save(request: HouseSaveRequest) : Long {
        return houseRepository.save(House(
            jibunAddress = request.jibunAddress,
            roadAddress = request.roadAddress,
            cost = request.cost,
            latitude = request.latitude,
            longitude = request.longitude
        )).id!!
    }

    @Transactional(readOnly = true)
    fun findByQuery(query: HouseGetQuery) : HouseGetResponse? {
        val house = query.jibunAddress?.let { houseRepository.findByJibunAddress(it) }
            ?: query.roadAddress?.let { houseRepository.findByRoadAddress(it) }
            ?: query.id?.let {
                houseRepository.findById(it)
                    .orElseThrow { IllegalArgumentException("해당 부동산이 존재하지 않습니다.") }
            }
        return if (house != null) {
            HouseGetResponse(
                id = house.id!!,
                jibunAddress = house.jibunAddress,
                roadAddress = house.roadAddress,
                cost = house.cost,
                latitude = house.latitude,
                longitude = house.longitude,
                createdDate = house.createdDate.toString(),
                modifiedDate = house.modifiedDate.toString()
            )
        } else null;
    }

    @Transactional
    fun updateByQuery(query : HouseGetQuery, request: HouseUpdateRequest) {
        val house = query.jibunAddress?.let { houseRepository.findByJibunAddress(it) }
            ?: query.roadAddress?.let { houseRepository.findByRoadAddress(it) }
            ?: query.id?.let { houseRepository.findById(it)
                .orElse(null)
            } ?: throw IllegalArgumentException("해당 부동산이 존재하지 않습니다.")

        request.jibunAddress?.let { house.updateJibunAddress(it) }
        request.roadAddress?.let { house.updateRoadAddress(it) }
        request.cost?.let { house.updateCost(it) }
        request.latitude?.let { house.updateLatitude(it) }
        request.longitude?.let { house.updateLongitude(it) }
    }

    @Transactional
    fun deleteByQuery(query : HouseGetQuery) {
        houseRepository.delete(
            query.jibunAddress?.let { houseRepository.findByJibunAddress(it) }
            ?: query.roadAddress?.let { houseRepository.findByRoadAddress(it) }
            ?: query.id?.let { houseRepository.findById(it)
                .orElse(null)
            } ?: throw IllegalArgumentException("해당 부동산이 존재하지 않습니다.")
        )
    }
}