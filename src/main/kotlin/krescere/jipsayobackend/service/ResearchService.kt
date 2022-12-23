package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.ResearchSaveRequest
import krescere.jipsayobackend.entity.House
import krescere.jipsayobackend.entity.Research
import krescere.jipsayobackend.repository.HouseRepository
import krescere.jipsayobackend.repository.ResearchRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ResearchService(
    private val researchRepository: ResearchRepository,
    private val houseRepository: HouseRepository
) {
    @Transactional
    fun save(request: ResearchSaveRequest) : Long {
        val house = request.jibunAddress?.let { houseRepository.findByJibunAddress(it) }
            ?: request.roadAddress?.let { houseRepository.findByRoadAddress(it) }
        return researchRepository.save(Research(
            savedMoney = request.savedMoney,
            moneyPerMonth = request.moneyPerMonth,
            house = house,
            increaseRate = request.increaseRate,
            job = request.job,
            occupation = request.occupation
        )).id!!
    }
}