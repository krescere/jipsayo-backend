package krescere.jipsayobackend.entity

import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "deal")
@Entity
class Deal (
    cost: Long,
    dealDate: LocalDateTime,
    houseDetail: HouseDetail
) {
    @Column(nullable = false)
    var cost: Long = cost
        private set

    @Column(nullable = false)
    var dealDate: LocalDateTime = dealDate
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_detail_id", nullable = false)
    var houseDetail: HouseDetail? = null
        private set

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    init {
        this.setHouseDetail(houseDetail)
    }
    private fun setHouseDetail(houseDetail: HouseDetail) {
        // 기존에 연관관계가 있으면 제거
        this.houseDetail?.deals?.remove(this)
        // 연관관계 설정
        this.houseDetail = houseDetail
        houseDetail.deals.add(this)
    }
}