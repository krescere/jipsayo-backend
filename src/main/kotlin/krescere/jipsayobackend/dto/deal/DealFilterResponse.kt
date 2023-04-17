package krescere.jipsayobackend.dto.deal

import java.math.BigDecimal
import java.time.LocalDateTime

data class DealFilterResponse(
    val houseId: Long,
    val roadAddress: String,
    val danjiName: String,
    val cost: Long,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val dealDate: LocalDateTime,
    val dedicatedArea: BigDecimal
)