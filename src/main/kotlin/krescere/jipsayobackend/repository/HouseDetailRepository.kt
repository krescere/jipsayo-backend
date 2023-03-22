package krescere.jipsayobackend.repository

import krescere.jipsayobackend.entity.HouseDetail
import org.springframework.data.jpa.repository.JpaRepository

interface HouseDetailRepository : JpaRepository<HouseDetail, Long> {
}