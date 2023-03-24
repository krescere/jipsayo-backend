package krescere.jipsayobackend.dto.dealHistory

class DealHistorySaveRequest(
    val pageNo: Int,
    val numOfRows: Int,
    val LAWD_CD: Int,
    val DEAL_YMD: Int
)