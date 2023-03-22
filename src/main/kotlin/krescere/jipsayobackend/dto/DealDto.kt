package krescere.jipsayobackend.dto

import krescere.jipsayobackend.entity.House
import java.time.LocalDateTime

class DealDto(
    cost: String,
    dealYear: String,
    dealMonth: String,
    dealDay: String,
    house: House,
) {
    var cost: Long = cost.toLong()
        private set
    var dealDate: LocalDateTime = LocalDateTime.of(dealYear.toInt(), dealMonth.toInt(), dealDay.toInt(), 0, 0)
        private set
    var house: House = house
        private set
}