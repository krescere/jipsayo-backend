package krescere.jipsayobackend.repository

import krescere.jipsayobackend.entity.House
import org.springframework.data.jpa.repository.JpaRepository

interface HouseRepository : JpaRepository<House, Long> {
    fun findByRoadAddressAndDanjiName(roadAddress: String, danjiName: String): House?
}