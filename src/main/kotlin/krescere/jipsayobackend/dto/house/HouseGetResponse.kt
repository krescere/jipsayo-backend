package krescere.jipsayobackend.dto.house

import krescere.jipsayobackend.dto.houseDetail.HouseDetailGetResponse
import krescere.jipsayobackend.entity.House
import java.math.BigDecimal

class HouseGetResponse(
    val id: Long,
    val jibunAddress: String,
    val roadAddress: String,
    val hangCode: Long,
    val danjiName: String,
    val postCode: Int,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val houseDetailGetResponses: List<HouseDetailGetResponse>
) {
    constructor(house: House) : this(
        id = house.id!!,
        jibunAddress = house.jibunAddress,
        roadAddress = house.roadAddress,
        hangCode = house.hangCode,
        danjiName = house.danjiName,
        postCode = house.postCode,
        latitude = house.latitude,
        longitude = house.longitude,
        houseDetailGetResponses = house.houseDetails.map { HouseDetailGetResponse(it) }
    )
}