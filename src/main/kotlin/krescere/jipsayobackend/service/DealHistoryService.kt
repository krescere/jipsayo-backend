package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.deal.DealFilterResponse
import krescere.jipsayobackend.dto.deal.DealSaveRequest
import krescere.jipsayobackend.dto.dealHistory.DealHistoryFilterRequest
import krescere.jipsayobackend.dto.dealHistory.DealHistoryFilterResponse
import krescere.jipsayobackend.dto.dealHistory.LawDealHistory
import krescere.jipsayobackend.dto.dealHistory.DealHistorySaveRequest
import krescere.jipsayobackend.dto.houseDetail.HouseDetailGetRequest
import krescere.jipsayobackend.service.handler.LawDealHistoryHandler
import krescere.jipsayobackend.service.handler.PredictHandler
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class DealHistoryService(
    private val dealService: DealService,
    private val houseService: HouseService,
    private val houseDetailService: HouseDetailService,
    private val lawDealHistoryHandler: LawDealHistoryHandler,
    private val predictHandler: PredictHandler
) {
    // filter 환경변수
    private val DEFAULT_FILTER_COUNT = 500
    private val MAX_FILTER_COUNT = 500

    @PersistenceContext
    private val entityManager: EntityManager? = null
    // save
    @Transactional
    fun save(request: DealHistorySaveRequest) {
        val lawDealHistories = lawDealHistoryHandler.getLawDealHistories(request)

        for(lawDealHistory in lawDealHistories) {
            // check if dealDto is not null
            if(!isDealDtoNotNull(lawDealHistory)) continue
            // dealHistory to house
            val house = houseService.findHouseOrSave(lawDealHistory)
            // dealHistory to houseDetail
            val houseDetail = houseDetailService.findHouseDetailOrSave(
                HouseDetailGetRequest(
                dedicatedArea = lawDealHistory.dedicatedArea!!,
                house = house
            )
            )
            // dealHistory to dealDto
            dealService.save(
                DealSaveRequest(
                cost = lawDealHistory.cost!!,
                dealYear = lawDealHistory.dealYear!!,
                dealMonth = lawDealHistory.dealMonth!!,
                dealDay = lawDealHistory.dealDay!!,
                houseDetail = houseDetail
            ))
        }
    }

    @Transactional
    fun predict(request: DealHistoryFilterRequest): List<DealHistoryFilterResponse> {
        // [id, {id,cost}] 형태의 map
        val candidatesMap= predictHandler.getCandidateMap(request)
        val ret = mutableListOf<DealHistoryFilterResponse>()

        val streamDeals=dealService.findDealsByCostRange(request.lowCost,request.highCost)

        streamDeals.forEach {
            ret.add(DealHistoryFilterResponse(
                roadAddress = it.roadAddress,
                danjiName = it.danjiName,
                cost = it.cost,
                latitude = it.latitude,
                longitude = it.longitude,
                time = candidatesMap[it.houseId]!!.time,
                dealDate = it.dealDate,
                dedicatedArea = it.dedicatedArea
            ))
            // detach
            entityManager!!.detach(it)
        }

        var count = request.count?:DEFAULT_FILTER_COUNT
        if(count > MAX_FILTER_COUNT) count = MAX_FILTER_COUNT
        ret.shuffle()
        return ret.take(count)
    }

    private fun isDealDtoNotNull(lawDealHistory: LawDealHistory) : Boolean {
        return lawDealHistory.cost != null &&
                lawDealHistory.dealYear != null &&
                lawDealHistory.dealMonth != null &&
                lawDealHistory.dealDay != null &&
                lawDealHistory.dedicatedArea != null &&
                lawDealHistory.roadNameCityCode != null

    }
}