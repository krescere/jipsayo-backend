package krescere.jipsayobackend.dto.house

import krescere.jipsayobackend.common.handler.DecimalPointHandler.Companion.doubleToBigDecimal
import krescere.jipsayobackend.dto.common.KakaoAddressResponse
import java.math.BigDecimal

class HouseSaveRequest (
    val jibunAddress: String,
    val roadAddress: String,
    val hangCode: Long,
    val danjiName: String,
    val postCode: Int,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
) {
    constructor(
        jibunAddress: String,
        roadAddress: String,
        hangCode: Long,
        danjiName: String,
        postCode: Int,
        latitude: Double,
        longitude: Double
    ) : this(
        jibunAddress = jibunAddress,
        roadAddress = roadAddress,
        hangCode = hangCode,
        danjiName = danjiName,
        postCode = postCode,
        latitude = latitude.toBigDecimal(),
        longitude = longitude.toBigDecimal()
    )
    constructor(kakaoAddressResponse: KakaoAddressResponse) : this(
        jibunAddress = kakaoAddressResponse.jibunAddress,
        roadAddress = kakaoAddressResponse.roadAddress,
        hangCode = kakaoAddressResponse.hangCode,
        danjiName = kakaoAddressResponse.danjiName,
        postCode = kakaoAddressResponse.postCode,
        latitude = kakaoAddressResponse.latitude,
        longitude = kakaoAddressResponse.longitude,
    )
}