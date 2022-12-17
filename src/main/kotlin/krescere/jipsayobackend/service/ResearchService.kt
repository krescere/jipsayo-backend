package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.ResearchSaveRequest
import krescere.jipsayobackend.entity.House
import krescere.jipsayobackend.entity.Research
import krescere.jipsayobackend.repository.HouseRepository
import krescere.jipsayobackend.repository.ResearchRepository
import org.springframework.stereotype.Service

@Service
class ResearchService(
    private val researchRepository: ResearchRepository,
    private val houseRepository: HouseRepository
) {
    fun save(request: ResearchSaveRequest) : Long {
        return researchRepository.save(Research(
            savedMoney = request.savedMoney,
            moneyPerMonth = request.moneyPerMonth,
            house = houseRepository.findByJibunAddress(request.jibunAddress),
            increaseRate = request.increaseRate,
            job = request.job,
            occupation = request.occupation
        )).id!!
    }
}