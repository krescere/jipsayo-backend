package krescere.jipsayobackend.dto.dealHistory

import krescere.jipsayobackend.dto.house.HouseGetResponse
import krescere.jipsayobackend.dto.houseDetail.HouseDetailGetResponse
import krescere.jipsayobackend.entity.House

class DealHistoryGetResponse(
    val jibunAddress: String,
    val roadAddress: String,
    val danjiName: String,
    val postCode: Int,
    val latitude: Double,
    val longitude: Double,
    val houseDetails: List<HouseDetailGetResponse>
) {
    constructor(house: House) : this(
        jibunAddress = house.jibunAddress,
        roadAddress = house.roadAddress,
        danjiName = house.danjiName,
        postCode = house.postCode,
        latitude = house.latitude.toDouble(),
        longitude = house.longitude.toDouble(),
        houseDetails = house.houseDetails.map { HouseDetailGetResponse(it) }
    )
}