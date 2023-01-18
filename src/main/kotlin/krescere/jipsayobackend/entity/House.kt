package krescere.jipsayobackend.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@EntityListeners(AuditingEntityListener::class)
@Table(name = "house", indexes = [
    Index(name = "idx_roadAddress_danjiName", columnList = "roadAddress, danjiName", unique = true)
])
@Entity
class House(
    jibunAddress: String,
    roadAddress: String,
    cost: Long,
    hangCode: Long,
    danjiName: String,
    postCode: Int,
    latitude: BigDecimal,
    longitude: BigDecimal,
    dealDate: LocalDateTime,
    dedicatedArea: Double,
) {
    @Column(nullable = false)
    var jibunAddress: String = jibunAddress
        private set

    @Column(nullable = false)
    var roadAddress: String = roadAddress
        private set

    @Column(nullable = false)
    var cost: Long = cost
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

    @Column(nullable = false, scale = 7) // 소수점 7자리까지
    var latitude: BigDecimal = latitude
        private set

    @Column(nullable = false, scale = 7) // 소수점 7자리까지
    var longitude: BigDecimal = longitude
        private set

    @Column(nullable = false)
    var dealDate: LocalDateTime = dealDate
        private set

    @Column(nullable = false)
    var dedicatedArea: Double = dedicatedArea
        private set

    @Column(nullable = false)
    @CreatedDate
    var createdDate: LocalDateTime = LocalDateTime.MIN
        private set

    @Column(nullable = false)
    @LastModifiedDate
    var modifiedDate: LocalDateTime = LocalDateTime.MIN
        private set

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    // update
    fun updateJibunAddress(jibunAddress: String) {
        this.jibunAddress = jibunAddress
    }

    fun updateRoadAddress(roadAddress: String) {
        this.roadAddress = roadAddress
    }

    fun updateCost(cost: Long) {
        this.cost = cost
    }

    fun updateHangCode(hangCode: Long) {
        this.hangCode = hangCode
    }

    fun updateDanjiName(danjiName: String) {
        this.danjiName = danjiName
    }

    fun updatePostCode(postCode: Int) {
        this.postCode = postCode
    }

    fun updateLatitude(latitude: BigDecimal) {
        this.latitude = latitude
    }

    fun updateLongitude(longitude: BigDecimal) {
        this.longitude = longitude
    }

    fun updateDealDate(dealDate: LocalDateTime) {
        this.dealDate = dealDate
    }

    fun updateDedicatedArea(dedicatedArea: Double) {
        this.dedicatedArea = dedicatedArea
    }
}