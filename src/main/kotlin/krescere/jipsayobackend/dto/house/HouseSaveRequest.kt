package krescere.jipsayobackend.dto.house

import krescere.jipsayobackend.common.DecimalPointHandler.Companion.doubleToBigDecimal
import krescere.jipsayobackend.dto.common.KakaoAddressResponse
import java.math.BigDecimal

class HouseSaveRequest (
    jibunAddress: String,
    roadAddress: String,
    hangCode: Long,
    danjiName: String,
    postCode: Int,
    latitude: Double,
    longitude: Double,
) {
    val jibunAddress: String = jibunAddress
    val roadAddress: String = roadAddress
    val hangCode: Long = hangCode
    val danjiName: String = danjiName
    val postCode: Int = postCode
    val latitude: BigDecimal = doubleToBigDecimal(latitude)
    val longitude: BigDecimal = doubleToBigDecimal(longitude)

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