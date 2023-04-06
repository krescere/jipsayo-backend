package krescere.jipsayobackend.dto.dealHistory

import java.time.LocalDateTime

class DealHistoryFilterResponse(
    roadAddress: String,
    danjiName: String,
    cost: Long,
    latitude: Double,
    longitude: Double,
    time: Long,
    dealDate: LocalDateTime,
    dedicatedArea: Double,
)