package krescere.jipsayobackend.dto.dealHistory

import krescere.jipsayobackend.dto.deal.DealFilterResponse
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
) {
    constructor(dealFilterResponse: DealFilterResponse, time: Long): this(
        roadAddress = dealFilterResponse.roadAddress,
        danjiName = dealFilterResponse.danjiName,
        cost = dealFilterResponse.cost,
        latitude = dealFilterResponse.latitude,
        longitude = dealFilterResponse.longitude,
        time = time,
        dealDate = dealFilterResponse.dealDate,
        dedicatedArea = dealFilterResponse.dedicatedArea)
}