package krescere.jipsayobackend.dto

class HouseGetResponse(
    id: Long,
    jibunAddress: String,
    cost: Long,
    latitude: Double?,
    longitude: Double?
) {
    var id: Long = id
        private set
    var jibunAddress: String = jibunAddress
        private set
    var cost: Long = cost
        private set
    var latitude: Double ?= latitude
        private set
    var longitude: Double ?= longitude
        private set
}