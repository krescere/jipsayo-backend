package krescere.jipsayobackend.dto

data class DealGetByAddressResponse(
    val id: Long,
    val jibunAddress: String,
    val roadAddress: String,
    val hangCode: Long,
    val danjiName: String,
    val postCode: Int,
    val latitude: Double,
    val longitude: Double,
    val createdDate: String,
    val modifiedDate: String,
    val dealDetails: List<DealDetail>
)