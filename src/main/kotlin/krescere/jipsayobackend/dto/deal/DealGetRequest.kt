package krescere.jipsayobackend.dto.deal

import krescere.jipsayobackend.entity.HouseDetail
import java.time.LocalDateTime

class DealGetRequest(
    val cost: Long,
    val dealDate: LocalDateTime,
    val houseDetail: HouseDetail
)