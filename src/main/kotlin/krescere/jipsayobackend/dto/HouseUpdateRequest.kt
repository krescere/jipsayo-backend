package krescere.jipsayobackend.dto

import krescere.jipsayobackend.common.DecimalPointHandler.Companion.doubleToBigDecimal
import java.math.BigDecimal
import java.time.LocalDateTime

class HouseUpdateRequest (
    jibunAddress: String?,
    roadAddress: String?,
    cost: Long?,
    hangCode: Long?,
    danjiName: String?,
    postCode: Int?,
    latitude: Double?,
    longitude: Double?,
    dealDate: String?,
    dedicatedArea: Double?,
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
    var dealDate: LocalDateTime ?= dealDate?.let { LocalDateTime.parse(it) }
        private set
    var dedicatedArea: Double ?= dedicatedArea
        private set
}