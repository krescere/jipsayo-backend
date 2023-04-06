package krescere.jipsayobackend.dto.dealHistory

class DealHistorySaveRequest(
    val pageNo: Int,
    val numOfRows: Int,
    val lawdCd: String,
    val dearYmd: String
)