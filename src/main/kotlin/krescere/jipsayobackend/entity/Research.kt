package krescere.jipsayobackend.entity

import javax.persistence.*

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}