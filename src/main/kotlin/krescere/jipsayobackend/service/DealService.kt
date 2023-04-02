package krescere.jipsayobackend.service

import krescere.jipsayobackend.common.error.CustomException
import krescere.jipsayobackend.common.error.ErrorCode
import krescere.jipsayobackend.dto.deal.DealFilterResponse
import krescere.jipsayobackend.dto.deal.DealGetRequest
import krescere.jipsayobackend.dto.deal.DealGetResponse
import krescere.jipsayobackend.dto.deal.DealSaveRequest
import krescere.jipsayobackend.dto.houseDetail.HouseDetailGetRequest
import krescere.jipsayobackend.entity.Deal
import krescere.jipsayobackend.repository.DealQueryDSLRepository
import krescere.jipsayobackend.repository.DealRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Stream

@Service
class DealService(
    private val dealRepository: DealRepository,
    private val dealQueryDSLRepository: DealQueryDSLRepository,
    private val houseDetailService: HouseDetailService
) {
    val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!

    @Transactional
    fun save(request: DealSaveRequest) : Long {
        val houseDetail = houseDetailService.get(HouseDetailGetRequest(
            id = request.houseDetailId
        )) ?: throw CustomException(ErrorCode.HOUSE_DETAIL_NOT_FOUND, "houseDetail id : ${request.houseDetailId} not found")
        // db에 저장한다.
        return dealRepository.save(Deal(
            cost = request.cost,
            dealDate = request.dealDate,
            houseDetail = houseDetail
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
        return dealQueryDSLRepository.findDealsByCostRange(highCost, lowCost)
    }
}