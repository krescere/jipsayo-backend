package krescere.jipsayobackend.dto

data class HouseFilterGetRequest(
    val latitude: String,
    val longitude: String,
    val cost: Long,
    val time: Long,
)