package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.deal.DealFilterResponse
import krescere.jipsayobackend.dto.deal.DealGetRequest
import krescere.jipsayobackend.dto.deal.DealGetResponse
import krescere.jipsayobackend.dto.deal.DealSaveRequest
import krescere.jipsayobackend.entity.Deal
import krescere.jipsayobackend.repository.DealRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Stream

@Service
class DealService(
    private val dealRepository: DealRepository,
) {
    val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!

    @Transactional
    fun save(dealSaveRequest: DealSaveRequest) : Long {
        // db에 저장한다.
        return dealRepository.save(Deal(
            cost = dealSaveRequest.cost,
            dealDate = dealSaveRequest.dealDate,
            houseDetail = dealSaveRequest.houseDetail,
        )).id!!
    }

    fun find(request: DealGetRequest) : DealGetResponse? {
        return dealRepository.findByCostAndDealDateAndHouseDetail(
            request.cost,
            request.dealDate,
            request.houseDetail
        )?.let { DealGetResponse(it) }
    }

    @Transactional(readOnly = true)
    fun findDealsByCostRange(lowCost: Long, highCost: Long): Stream<DealFilterResponse>{
        return dealRepository.findDealsByCostRange(highCost, lowCost)
    }
}