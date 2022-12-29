package krescere.jipsayobackend.dto

class ResearchSaveRequest (
savedMoney: Long,
moneyPerMonth: Long,
roadAddress: String?,
danjiName: String?,
increaseRate: Double?,
job: String?,
occupation: String?
){
    var savedMoney: Long = savedMoney
        private set
    var moneyPerMonth: Long = moneyPerMonth
        private set
    var roadAddress: String? = roadAddress
        private set
    var danjiName: String? = danjiName
        private set
    var increaseRate: Double? = increaseRate
        private set
    var job: String? = job
        private set
    var occupation: String? = occupation
        private set
}