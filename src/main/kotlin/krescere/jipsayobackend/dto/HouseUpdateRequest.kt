package krescere.jipsayobackend.dto

import krescere.jipsayobackend.common.DecimalPointHandler.Companion.doubleToBigDecimal
import java.math.BigDecimal

class HouseUpdateRequest (
    jibunAddress: String?,
    roadAddress: String?,
    cost: Long?,
    hangCode: Long?,
    danjiName: String?,
    postCode: Int?,
    latitude: Double?,
    longitude: Double?
) {
    var jibunAddress: String ?= jibunAddress
        private set
    var roadAddress: String ?= roadAddress
        private set
    var cost: Long ?= cost
        private set
    var hangCode: Long ?= hangCode
        private set
    var danjiName: String ?= danjiName
        private set
    var postCode: Int ?= postCode
        private set
    var latitude: BigDecimal ?= latitude?.let { doubleToBigDecimal(it) }
        private set
    var longitude: BigDecimal ?= longitude?.let { doubleToBigDecimal(it) }
        private set
}