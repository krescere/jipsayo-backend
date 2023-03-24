package krescere.jipsayobackend.dto

class HouseFilterGetRequest(
    val latitude: String,
    val longitude: String,
    val lowCost: Long,
    val highCost: Long,
    val time: Long,
    val count: Int?,
)