package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.HouseGetResponse
import krescere.jipsayobackend.dto.HouseSaveRequest
import krescere.jipsayobackend.entity.House
import krescere.jipsayobackend.repository.HouseRepository
import org.springframework.stereotype.Service

@Service
class HouseService(
    private val houseRepository: HouseRepository
) {
    fun save(request: HouseSaveRequest) : Long {
        return houseRepository.save(House(
            jibunAddress = request.jibunAddress,
            cost = request.cost,
            latitude = request.latitude,
            longitude = request.longitude
        )).id!!
    }

    fun findByJibunAddress(jibunAddress: String) : HouseGetResponse? {
        return houseRepository.findByJibunAddress(jibunAddress)?.let {
            HouseGetResponse(
                id = it.id!!,
                jibunAddress = it.jibunAddress,
                cost = it.cost,
                latitude = it.latitude,
                longitude = it.longitude
            )
        }
    }
}