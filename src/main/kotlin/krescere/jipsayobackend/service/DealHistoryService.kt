package krescere.jipsayobackend.service

import krescere.jipsayobackend.common.handler.LawDealHistoryHandler
import krescere.jipsayobackend.common.handler.PredictHandler
import krescere.jipsayobackend.dto.deal.DealSaveRequest
import krescere.jipsayobackend.dto.dealHistory.*
import krescere.jipsayobackend.dto.house.HouseGetRequest
import krescere.jipsayobackend.dto.houseDetail.HouseDetailGetRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

// filter 환경변수
const val DEFAULT_FILTER_COUNT = 500
const val MAX_FILTER_COUNT = 500

@Service
class DealHistoryService(
    private val dealService: DealService,
    private val houseService: HouseService,
    private val houseDetailService: HouseDetailService,
    private val lawDealHistoryHandler: LawDealHistoryHandler,
    private val predictHandler: PredictHandler
) {
    val logger = LoggerFactory.getLogger(this.javaClass)!!

    @PersistenceContext
    private val entityManager: EntityManager? = null

    // return : total count, 전체 데이터 개수
    @Transactional
    fun save(request: DealHistorySaveRequest) : Int {
        val lawDealHistories = try {
            lawDealHistoryHandler.getLawDealHistories(request)
        } catch (e: Exception) {
            logger.error("getLawDealHistories error: ${e.message}")
            return 0
        }

        for(lawDealHistory in lawDealHistories) {
            try {
                // check if dealDto is not null
                if(!isDealDtoNotNull(lawDealHistory)) continue
                // dealHistory to house
                val house = houseService.getHouseOrSave(lawDealHistory)
                // dealHistory to houseDetail
                val houseDetail = houseDetailService.getHouseDetailOrSave(
                    HouseDetailGetRequest(
                        dedicatedArea = lawDealHistory.dedicatedArea!!,
                        houseId = house.id!!
                    ))
                // dealHistory to dealDto
                dealService.save(
                    DealSaveRequest(
                        cost = lawDealHistory.cost!!,
                        dealYear = lawDealHistory.dealYear!!,
                        dealMonth = lawDealHistory.dealMonth!!,
                        dealDay = lawDealHistory.dealDay!!,
                        houseDetailId = houseDetail.id!!
                    ))
            } catch (e: Exception) {
                logger.error("save dealHistory error: ${e.message}")
            }
        }

        return lawDealHistoryHandler.getTotalCount()
    }

    @Transactional(readOnly = true)
    fun find(request: DealHistoryGetRequest) : DealHistoryGetResponse? {
        return houseService.get(HouseGetRequest(
            roadAddress = request.roadAddress,
            danjiName = request.danjiName
        ))?.let { DealHistoryGetResponse(it) }
    }

    @Transactional(readOnly = true)
    fun filter(request: DealHistoryFilterRequest): List<DealHistoryFilterResponse> {
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