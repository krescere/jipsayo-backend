package krescere.jipsayobackend.repository

import krescere.jipsayobackend.entity.House
import org.springframework.data.jpa.repository.JpaRepository

interface HouseRepository : JpaRepository<House, Long> {
    fun findByJibunAddress(jibunAddress: String): House?
    fun findByRoadAddress(roadAddress: String): House?
}