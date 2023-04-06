package krescere.jipsayobackend.dto.houseDetail

import krescere.jipsayobackend.entity.House
import java.math.BigDecimal

class HouseDetailGetRequest(
    val id: Long? = null,
    dedicatedArea: String? = null,
    val houseId: Long? = null
) {
    val dedicatedArea: BigDecimal? = dedicatedArea?.toBigDecimal()
}