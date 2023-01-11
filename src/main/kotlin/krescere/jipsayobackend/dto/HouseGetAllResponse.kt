package krescere.jipsayobackend.dto

import krescere.jipsayobackend.entity.House

class HouseGetAllResponse (
    latitude: String,
    longitude: String,
    roadAddress: String,
    danjiName: String
){
    constructor(house: House) : this(
        house.location.x.toString(),
        house.location.y.toString(),
        house.roadAddress,
        house.danjiName
    )

    var latitude: String = latitude
        private set
    var longitude: String = longitude
        private set
    var roadAddress: String = roadAddress
        private set
    var danjiName: String = danjiName
        private set
}