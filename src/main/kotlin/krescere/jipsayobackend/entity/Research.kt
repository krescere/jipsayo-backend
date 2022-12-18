package krescere.jipsayobackend.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@EntityListeners(AuditingEntityListener::class)
@Entity
class Research (
    savedMoney: Long,
    moneyPerMonth: Long,
    house: House?,
    increaseRate: Double,
    job: String?,
    occupation: String?
) {
    @Column(nullable = false)
    var savedMoney: Long = savedMoney
        private set

    @Column(nullable = false)
    var moneyPerMonth: Long = moneyPerMonth
        private set

    @ManyToOne
    @JoinColumn(name = "HOUSE_ID")
    var house: House? = house
        private set

    @Column(nullable = false)
    var increaseRate: Double = increaseRate
        private set

    @Column(nullable = true)
    var job : String? = job
        private set

    @Column(nullable = true)
    var occupation : String? = occupation
        private set

    @CreatedDate
    var createdDate: LocalDateTime = LocalDateTime.MIN
        private set

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}