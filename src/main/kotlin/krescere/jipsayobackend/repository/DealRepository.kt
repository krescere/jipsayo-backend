package krescere.jipsayobackend.repository

import krescere.jipsayobackend.entity.Deal
import krescere.jipsayobackend.entity.HouseDetail
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface DealRepository : JpaRepository<Deal, Long> {
    fun findByCostAndDealDateAndHouseDetail(cost: Long, dealDate: LocalDateTime, houseDetail: HouseDetail): Deal?
}