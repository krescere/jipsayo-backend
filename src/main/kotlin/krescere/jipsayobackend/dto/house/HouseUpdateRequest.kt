package krescere.jipsayobackend.dto.house

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
    val jibunAddress: String ?= jibunAddress
    val roadAddress: String ?= roadAddress
    val cost: Long ?= cost
    val hangCode: Long ?= hangCode
    val danjiName: String ?= danjiName
    val postCode: Int ?= postCode
    val latitude: BigDecimal ?= latitude?.let { doubleToBigDecimal(it) }
    val longitude: BigDecimal ?= longitude?.let { doubleToBigDecimal(it) }
    val dealDate: LocalDateTime ?= dealDate?.let { LocalDateTime.parse(it) }
    val dedicatedArea: Double ?= dedicatedArea
}