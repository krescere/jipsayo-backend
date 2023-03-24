package krescere.jipsayobackend.dto.research

class ResearchSaveRequest (
    val savedMoney: Long,
    val moneyPerMonth: Long,
    val roadAddress: String?,
    val danjiName: String?,
    val increaseRate: Double?,
    val job: String?,
    val occupation: String?
)