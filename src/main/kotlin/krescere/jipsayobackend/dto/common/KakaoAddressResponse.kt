package krescere.jipsayobackend.dto.common

class KakaoAddressResponse(
    val jibunAddress: String,
    val roadAddress: String,
    val hangCode: Long,
    val danjiName: String,
    val postCode: Int,
    val latitude: Double,
    val longitude: Double,
)