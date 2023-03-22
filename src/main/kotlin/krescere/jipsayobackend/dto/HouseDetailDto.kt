package krescere.jipsayobackend.dto

import krescere.jipsayobackend.entity.House

class HouseDetailDto(
    dedicatedArea: String,
    house: House,
) {
    var dedicatedArea: Double = dedicatedArea.toDouble()
        private set
    var house: House = house
        private set
}