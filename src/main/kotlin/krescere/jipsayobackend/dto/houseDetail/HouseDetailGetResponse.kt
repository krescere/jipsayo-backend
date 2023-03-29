package krescere.jipsayobackend.dto.houseDetail

import krescere.jipsayobackend.dto.deal.DealGetResponse
import krescere.jipsayobackend.entity.HouseDetail

class HouseDetailGetResponse(
    val id: Long,
    val count: Int,
    val dedicatedArea: Double,
    val dealGetResponses: List<DealGetResponse>,
) {
    constructor(houseDetail: HouseDetail) : this(
        id = houseDetail.id!!,
        count = houseDetail.count,
        dedicatedArea = houseDetail.dedicatedArea.toDouble(),
        dealGetResponses = houseDetail.deals.map { DealGetResponse(it) }
    )
}