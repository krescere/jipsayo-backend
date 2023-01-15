package krescere.jipsayobackend.dto

import krescere.jipsayobackend.entity.House

class HouseGetResponse(
    id: Long,
    jibunAddress: String,
    roadAddress: String,
    cost: Long,
    hangCode: Long,
    danjiName: String,
    postCode: Int,
    latitude: String,
    longitude: String,
    createdDate: String,
    modifiedDate: String
) {
    // constructor
    constructor(house: House) : this(
        house.id!!,
        house.jibunAddress,
        house.roadAddress,
        house.cost,
        house.hangCode,
        house.danjiName,
        house.postCode,
        house.location.x.toString(),
        house.location.y.toString(),
        house.createdDate.toString(),
        house.modifiedDate.toString()
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
    var latitude: String = latitude
        private set
    var longitude: String = longitude
        private set
    var createdDate: String = createdDate
        private set
    var modifiedDate: String = modifiedDate
        private set
}