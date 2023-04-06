package krescere.jipsayobackend.dto.dealHistory

import krescere.jipsayobackend.dto.house.HouseGetResponse
import krescere.jipsayobackend.dto.houseDetail.HouseDetailGetResponse
import krescere.jipsayobackend.entity.House

class DealHistoryGetResponse(
    val house: HouseGetResponse
) {
    constructor(house: House) : this(
        house = HouseGetResponse(house)
    )
}