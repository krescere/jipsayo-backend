package krescere.jipsayobackend.dto

import krescere.jipsayobackend.common.DecimalPointHandler.Companion.bigDecimalToDouble
import krescere.jipsayobackend.entity.House

class HouseGetResponse(
    id: Long,
    jibunAddress: String,
    roadAddress: String,
    cost: Long,
    hangCode: Long,
    danjiName: String,
    postCode: Int,
    latitude: Double,
    longitude: Double,
    dealDate: String,
    dedicatedArea: Double,
    createdDate: String,
    modifiedDate: String
) {
    constructor(house: House) : this(
        id = house.id!!,
        jibunAddress = house.jibunAddress,
        roadAddress = house.roadAddress,
        cost = house.cost,
        hangCode = house.hangCode,
        danjiName = house.danjiName,
        postCode = house.postCode,
        latitude = bigDecimalToDouble(house.latitude),
        longitude = bigDecimalToDouble(house.longitude),
        dealDate = house.dealDate.toString(),
        dedicatedArea = house.dedicatedArea,
        createdDate = house.createdDate.toString(),
        modifiedDate = house.modifiedDate.toString()
    )

    var id: Long = id
        private set
    var jibunAddress: String = jibunAddress
        private set
    var roadAddress: String = roadAddress
        private set
    var cost: Long = cost
        private set
    var hangCode: Long = hangCode
        private set
    var danjiName: String = danjiName
        private set
    var postCode: Int = postCode
        private set
    var latitude: Double = latitude
        private set
    var longitude: Double = longitude
        private set
    var dealDate: String = dealDate
        private set
    var dedicatedArea: Double = dedicatedArea
        private set
    var createdDate: String = createdDate
        private set
    var modifiedDate: String = modifiedDate
        private set
}