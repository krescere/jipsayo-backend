package krescere.jipsayobackend.dto

class HouseUpdateRequest (
    cost: Long?,
    latitude: Double?,
    longitude: Double?
) {
    var cost: Long ?= cost
        private set
    var latitude: Double ?= latitude
        private set
    var longitude: Double ?= longitude
        private set
}