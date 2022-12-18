package krescere.jipsayobackend.dto

class HouseGetQuery(
    id: Long?,
    jibunAddress: String?,
    roadAddress: String?
) {
    var id: Long ?= id
        private set
    var jibunAddress: String ?= jibunAddress
        private set
    var roadAddress: String ?= roadAddress
        private set
}