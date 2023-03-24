package krescere.jipsayobackend.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import javax.persistence.*

@EntityListeners(AuditingEntityListener::class)
@Table(name = "house")
@Entity
class House(
    jibunAddress: String,
    roadAddress: String,
    hangCode: Long,
    danjiName: String,
    postCode: Int,
    latitude: BigDecimal,
    longitude: BigDecimal,
) {
    @Column(nullable = false)
    var jibunAddress: String = jibunAddress
        private set

    @Column(nullable = false)
    var roadAddress: String = roadAddress
        private set

    @Column(nullable = false)
    var hangCode: Long = hangCode
        private set

    @Column(nullable = false)
    var danjiName: String = danjiName
        private set

    @Column(nullable = false)
    var postCode: Int = postCode
        private set

    @Column(nullable = false)
    var latitude: BigDecimal = latitude
        private set

    @Column(nullable = false)
    var longitude: BigDecimal = longitude
        private set

    @OneToMany(mappedBy = "house")
    val houseDetails: List<HouseDetail> = mutableListOf()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}