package krescere.jipsayobackend.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

/**
 * House 와 1:N 관계를 맺는다.
 */
@EntityListeners(AuditingEntityListener::class)
@Table(name = "house_detail")
@Entity
class HouseDetail(
    dedicatedArea: Double,
    count: Int,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}