package krescere.jipsayobackend.entity

import java.math.BigDecimal
import javax.persistence.*

@Table(name = "house", indexes = [
    Index(name = "idx_roadAddress_danjiName", columnList = "roadAddress, danjiName", unique = true),
])
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

    @Column(nullable = false, precision = 10, scale = 7)
    var latitude: BigDecimal = latitude
        private set

    @Column(nullable = false, precision = 10, scale = 7)
    var longitude: BigDecimal = longitude
        private set

    @OneToMany(mappedBy = "house", cascade = [CascadeType.REMOVE])
    val houseDetails: MutableCollection<HouseDetail> = mutableSetOf()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}