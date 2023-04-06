package krescere.jipsayobackend.repository

import krescere.jipsayobackend.entity.House
import krescere.jipsayobackend.entity.HouseDetail
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal

interface HouseDetailRepository : JpaRepository<HouseDetail, Long> {
    fun findByDedicatedAreaAndHouse(dedicatedArea: BigDecimal, house: House): HouseDetail?
}