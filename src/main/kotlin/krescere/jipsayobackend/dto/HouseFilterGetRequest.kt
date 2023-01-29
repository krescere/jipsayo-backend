package krescere.jipsayobackend.dto

data class HouseFilterGetRequest(
    val latitude: String,
    val longitude: String,
    val lowCost: Long,
    val highCost: Long,
    val time: Long,
    val count: Int?,
)