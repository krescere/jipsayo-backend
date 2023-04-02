package krescere.jipsayobackend.dto.common

import java.math.BigDecimal

class KakaoAddressResponse(
    val jibunAddress: String,
    val roadAddress: String,
    val hangCode: Long,
    val danjiName: String,
    val postCode: Int,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
)