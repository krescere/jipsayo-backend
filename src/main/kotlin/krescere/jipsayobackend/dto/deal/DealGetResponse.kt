package krescere.jipsayobackend.dto.deal

import krescere.jipsayobackend.entity.Deal
import java.time.LocalDateTime

class DealGetResponse(
    val id: Long,
    val cost: Long,
    val dealDate: LocalDateTime
) {
    constructor(deal: Deal) : this(
        id = deal.id!!,
        cost = deal.cost,
        dealDate = deal.dealDate
    )
}