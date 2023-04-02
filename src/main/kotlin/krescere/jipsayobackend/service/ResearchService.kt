package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.house.HouseGetRequest
import krescere.jipsayobackend.dto.research.ResearchSaveRequest
import krescere.jipsayobackend.entity.Research
import krescere.jipsayobackend.repository.HouseRepository
import krescere.jipsayobackend.repository.ResearchRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ResearchService(
    private val researchRepository: ResearchRepository,
    private val houseService: HouseService,
) {
    @Transactional
    fun save(request: ResearchSaveRequest) : Long {
        val house =
            if(request.roadAddress!=null && request.danjiName!=null) {
                houseService.get(
                    HouseGetRequest(
                    roadAddress = request.roadAddress,
                    danjiName = request.danjiName
                ))
            }
            else null

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