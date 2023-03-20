package krescere.jipsayobackend.dto

import krescere.jipsayobackend.common.DecimalPointHandler.Companion.bigDecimalToDouble
import krescere.jipsayobackend.common.DecimalPointHandler.Companion.roundToPoint
import java.time.LocalDateTime

class HouseFilterGetResponse(
    id: Long,
    jibunAddress: String,
    danjiName: String,
    cost: Long,
    latitude: Double,
    longitude: Double,
    time: Long,
    dealDate: LocalDateTime,
    dedicatedArea: Double,
){
    constructor(house : House,time: Long) : this(
        id = house.id!!,
        jibunAddress = house.jibunAddress,
        danjiName = house.danjiName,
        cost = house.cost,
        latitude = bigDecimalToDouble(house.latitude),
        longitude = bigDecimalToDouble(house.longitude),
        time = time,
        dealDate = house.dealDate,
        dedicatedArea = roundToPoint(house.dedicatedArea,3),
    )
    var id: Long = id
        private set
    var jibunAddress: String = jibunAddress
        private set
    var danjiName: String = danjiName
        private set
    var cost: Long = cost
        private set
    var latitude: Double = latitude
        private set
    var longitude: Double = longitude
        private set
    var time: Long = time
        private set
    var dealDate: LocalDateTime = dealDate
        private set
    var dedicatedArea: Double = dedicatedArea
        private set
}