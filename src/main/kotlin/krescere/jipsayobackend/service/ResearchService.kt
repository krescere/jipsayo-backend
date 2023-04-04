package krescere.jipsayobackend.service

import krescere.jipsayobackend.common.error.CustomException
import krescere.jipsayobackend.common.error.ErrorCode
import krescere.jipsayobackend.dto.house.HouseGetRequest
import krescere.jipsayobackend.dto.houseDetail.HouseDetailGetRequest
import krescere.jipsayobackend.dto.research.ResearchSaveRequest
import krescere.jipsayobackend.entity.Research
import krescere.jipsayobackend.repository.HouseRepository
import krescere.jipsayobackend.repository.ResearchRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ResearchService(
    private val researchRepository: ResearchRepository,
    private val houseDetailService: HouseDetailService
) {
    @Transactional
    fun save(request: ResearchSaveRequest) : Long {
        val houseDetail = houseDetailService.get(HouseDetailGetRequest(id = request.houseDetailId))
            ?: throw CustomException(ErrorCode.HOUSE_DETAIL_NOT_FOUND, "houseDetail not found id: ${request.houseDetailId}")

        return researchRepository.save(Research(
            houseDetail = houseDetail,
            savedMoney = request.savedMoney,
            moneyPerMonth = request.moneyPerMonth,
            increaseRate = request.increaseRate,
            job = request.job,
            occupation = request.occupation
        )).id!!
    }
}