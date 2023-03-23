package krescere.jipsayobackend.dto

data class KakaoAddressResponse(
    val jibunAddress: String,
    val roadAddress: String,
    val hangCode: Long,
    val danjiName: String,
    val postCode: Int,
    val latitude: Double,
    val longitude: Double,
)