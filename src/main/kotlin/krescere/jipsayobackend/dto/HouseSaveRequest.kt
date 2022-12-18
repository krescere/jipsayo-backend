package krescere.jipsayobackend.dto

class HouseSaveRequest (
    jibunAddress: String,
    roadAddress: String,
    cost: Long,
    latitude: Double?,
    longitude: Double?
) {
    var jibunAddress: String = jibunAddress
        private set
    var roadAddress: String = roadAddress
        private set
    var cost: Long = cost
        private set
    var latitude: Double ?= latitude
        private set
    var longitude: Double ?= longitude
        private set
}