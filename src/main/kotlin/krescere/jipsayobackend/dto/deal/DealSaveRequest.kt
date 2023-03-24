package krescere.jipsayobackend.dto.deal

import krescere.jipsayobackend.entity.HouseDetail
import java.time.LocalDateTime

class DealSaveRequest(
    cost: String,
    dealYear: String,
    dealMonth: String,
    dealDay: String,
    houseDetail: HouseDetail
) {
    val cost: Long = cost.toLong()
    val dealDate: LocalDateTime = LocalDateTime.of(dealYear.toInt(), dealMonth.toInt(), dealDay.toInt(), 0, 0)
    val houseDetail: HouseDetail = houseDetail
}