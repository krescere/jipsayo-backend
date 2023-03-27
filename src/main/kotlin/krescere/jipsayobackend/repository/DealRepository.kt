package krescere.jipsayobackend.repository

import krescere.jipsayobackend.dto.deal.DealFilterResponse
import krescere.jipsayobackend.entity.Deal
import krescere.jipsayobackend.entity.HouseDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime
import java.util.stream.Stream

interface DealRepository : JpaRepository<Deal, Long> {
    fun findByCostAndDealDateAndHouseDetail(cost: Long, dealDate: LocalDateTime, houseDetail: HouseDetail): Deal?

    @Query("select new krescere.jipsayobackend.dto.deal.DealFilterResponse" +
            "(h.id, h.roadAddress, h.danjiName, d.cost, h.latitude, h.longitude, d.dealDate, hd.dedicatedArea) " +
            "from Deal d " +
            "join fetch d.houseDetail hd " +
            "join fetch hd.house h " +
            "where d.cost < :highCost and d.cost > :lowCost " +
            "and d.dealDate = " +
            "(select max(d2.dealDate) " +
            "from Deal d2 " +
            "join d2.houseDetail hd2 " +
            "join hd2.house h2 " +
            "where h2.roadAddress = h.roadAddress " +
            "and h2.danjiName = h.danjiName) " +
            "group by h.roadAddress, h.danjiName ")
    fun findDealsByCostRange(@Param("highCost") highCost: Long, @Param("lowCost") lowCost: Long): Stream<DealFilterResponse>
}