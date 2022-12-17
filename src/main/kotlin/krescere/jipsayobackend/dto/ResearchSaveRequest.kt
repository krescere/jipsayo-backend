package krescere.jipsayobackend.dto

class ResearchSaveRequest (
savedMoney: Long,
moneyPerMonth: Long,
jibunAddress: String,
increaseRate: Double,
job: String?,
occupation: String?
){
    var savedMoney: Long = savedMoney
        private set
    var moneyPerMonth: Long = moneyPerMonth
        private set
    var jibunAddress: String = jibunAddress
        private set
    var increaseRate: Double = increaseRate
        private set
    var job: String? = job
        private set
    var occupation: String? = occupation
        private set
}