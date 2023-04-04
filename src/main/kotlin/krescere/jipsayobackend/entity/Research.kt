package krescere.jipsayobackend.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "research")
@Entity
class Research (
    savedMoney: Long,
    moneyPerMonth: Long,
    houseDetail: HouseDetail,
    increaseRate: Double?,
    job: String?,
    occupation: String?
) {
    @Column(nullable = false)
    var savedMoney: Long = savedMoney
        private set

    @Column(nullable = false)
    var moneyPerMonth: Long = moneyPerMonth
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_detail_id", nullable = false)
    var houseDetail: HouseDetail = houseDetail
        private set

    @Column(nullable = false)
    var increaseRate: Double? = increaseRate
        private set

    @Column(nullable = true)
    var job : String? = job
        private set

    @Column(nullable = true)
    var occupation : String? = occupation
        private set

    @CreatedDate
    @Column(nullable = false)
    var createdDate: LocalDateTime = LocalDateTime.MIN
        private set

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}