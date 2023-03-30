package krescere.jipsayobackend.dto.dealHistory

class DealHistorySaveRequest(
    val pageNo: Int,
    val numOfRows: Int,
    val LAWD_CD: String,
    val DEAL_YMD: String
)