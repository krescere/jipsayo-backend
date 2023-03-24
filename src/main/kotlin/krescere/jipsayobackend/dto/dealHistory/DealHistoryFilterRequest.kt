package krescere.jipsayobackend.dto.dealHistory

class DealHistoryFilterRequest(
    val latitude: String,
    val longitude: String,
    val lowCost: Long,
    val highCost: Long,
    val time: Long,
    val count: Int?,
)