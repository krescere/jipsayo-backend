package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.DealDto
import krescere.jipsayobackend.dto.DealGetResponse
import krescere.jipsayobackend.dto.HouseGetQuery
import krescere.jipsayobackend.entity.Deal
import krescere.jipsayobackend.repository.DealRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DealService(
    private val dealRepository: DealRepository,
) {
    val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)!!

    @Transactional
    fun save(dealDto: DealDto) {
        // db에 저장한다.
        dealRepository.save(Deal(
            cost = dealDto.cost,
            dealDate = dealDto.dealDate,
            house = dealDto.house
        ))
    }

    fun findByQuery(query: HouseGetQuery) : DealGetResponse? {
        return null
    }
}