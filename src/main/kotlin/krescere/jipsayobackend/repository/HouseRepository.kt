package krescere.jipsayobackend.repository

import krescere.jipsayobackend.entity.House
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.stream.Stream

interface HouseRepository : JpaRepository<House, Long> {
    fun findByRoadAddressAndDanjiName(roadAddress: String, danjiName: String): House?

    @Query("select h from House h where h.cost <= :cost")
    fun streamByCostBefore(@Param("cost") cost: Long): Stream<House>
}