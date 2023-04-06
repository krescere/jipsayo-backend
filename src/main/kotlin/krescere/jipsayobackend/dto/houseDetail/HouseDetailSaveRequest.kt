package krescere.jipsayobackend.dto.houseDetail

import krescere.jipsayobackend.entity.House
import java.math.BigDecimal

class HouseDetailSaveRequest(
    val dedicatedArea: BigDecimal,
    val house: House
)