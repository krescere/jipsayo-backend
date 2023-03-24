package krescere.jipsayobackend.dto.houseDetail

import krescere.jipsayobackend.entity.House
import java.math.BigDecimal

class HouseDetailGetRequest(
    dedicatedArea: String,
    house: House,
) {
    val dedicatedArea: BigDecimal = dedicatedArea.toBigDecimal()
    val house: House = house
}