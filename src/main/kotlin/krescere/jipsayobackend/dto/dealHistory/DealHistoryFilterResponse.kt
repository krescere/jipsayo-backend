package krescere.jipsayobackend.dto.dealHistory

import krescere.jipsayobackend.dto.deal.DealFilterResponse
import java.time.LocalDateTime

data class DealHistoryFilterResponse(
    val roadAddress: String,
    val danjiName: String,
    val cost: Long,
    val latitude: Double,
    val longitude: Double,
    val time: Long,
    val dealDate: LocalDateTime,
    val dedicatedArea: Double,
) {
    constructor(dealFilterResponse: DealFilterResponse, time: Long): this(
        roadAddress = dealFilterResponse.roadAddress,
        danjiName = dealFilterResponse.danjiName,
        cost = dealFilterResponse.cost,
        latitude = dealFilterResponse.latitude.toDouble(),
        longitude = dealFilterResponse.longitude.toDouble(),
        time = time,
        dealDate = dealFilterResponse.dealDate,
        dedicatedArea = dealFilterResponse.dedicatedArea.toDouble())
}