package krescere.jipsayobackend.entity

import java.math.BigDecimal
import javax.persistence.*

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOUSE_ID", nullable = false)
    var house: House? = house
        private set

    @OneToMany(mappedBy = "houseDetail", cascade = [CascadeType.REMOVE])
    val deals : MutableCollection<Deal> = mutableSetOf()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    init {
        this.setHouse(house)
    }
    private fun setHouse(house: House) {
        // 기존에 연관관계가 있으면 제거
        this.house?.houseDetails?.remove(this)
        // 연관관계 설정
        this.house = house
        house.houseDetails.add(this)
    }

    @PrePersist
    fun prePersist() {
        // count==null 이면  0으로 초기화
        this.count = this.count ?: 0
    }
}