package krescere.jipsayobackend.dto.deal

import java.time.LocalDateTime

data class DealFilterResponse(
    val houseId: Long,
    val roadAddress: String,
    val danjiName: String,
    val cost: Long,
    val latitude: Double,
    val longitude: Double,
    val dealDate: LocalDateTime,
    val dedicatedArea: Double
)