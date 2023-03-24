package krescere.jipsayobackend.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import javax.persistence.*

/**
 * House 와 1:N 관계를 맺는다.
 */
@EntityListeners(AuditingEntityListener::class)
@Table(name = "house_detail")
@Entity
class HouseDetail(
    dedicatedArea: BigDecimal,
    house: House,
) {
    @Column(nullable = false)
    var dedicatedArea: BigDecimal = dedicatedArea
        private set

    @Column(nullable = false)
    var count: Int = 0
        private set

    @ManyToOne
    @JoinColumn(name = "HOUSE_ID", nullable = false)
    var house: House = house
        private set

    @OneToMany(mappedBy = "houseDetail")
    val deals : List<Deal> = mutableListOf()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @PrePersist
    fun prePersist() {
        // count==null 이면  0으로 초기화
        this.count = this.count ?: 0
    }
}