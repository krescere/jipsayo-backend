package krescere.jipsayobackend.dto.research

class ResearchSaveRequest (
    val houseDetailId: Long,
    val savedMoney: Long,
    val moneyPerMonth: Long,
    val increaseRate: Double?,
    val job: String?,
    val occupation: String?,
)