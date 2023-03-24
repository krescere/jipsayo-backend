package krescere.jipsayobackend.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

/**
 * House 와 1:N 관계를 맺는다.
 */
@EntityListeners(AuditingEntityListener::class)
@Table(name = "deal")
@Entity
class Deal(
    cost: Long,
    dealDate: LocalDateTime,
    houseDetail: HouseDetail,
) {
    @Column(nullable = false)
    var cost: Long = cost
        private set

    @Column(nullable = false)
    var dealDate: LocalDateTime = dealDate
        private set

    @ManyToOne
    @JoinColumn(name = "house_detail_id")
    var houseDetail: HouseDetail = houseDetail
        private set

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}