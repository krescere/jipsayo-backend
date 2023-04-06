package krescere.jipsayobackend.dto.deal

import java.math.BigDecimal
import java.time.LocalDateTime

class DealFilterResponse(
    val houseId: Long,
    val roadAddress: String,
    val danjiName: String,
    val cost: Long,
    val latitude: Double,
    val longitude: Double,
    val dealDate: LocalDateTime,
    val dedicatedArea: Double
) {
    constructor(
        houseId: Long,
        roadAddress: String,
        danjiName: String,
        cost: Long,
        latitude: BigDecimal,
        longitude: BigDecimal,
        dealDate: LocalDateTime,
        dedicatedArea: BigDecimal
    ): this (
        houseId = houseId,
        roadAddress = roadAddress,
        danjiName = danjiName,
        cost = cost,
        latitude = latitude.toDouble(),
        longitude = longitude.toDouble(),
        dealDate = dealDate,
        dedicatedArea = dedicatedArea.toDouble()
    )
}