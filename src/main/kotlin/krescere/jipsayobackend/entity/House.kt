package krescere.jipsayobackend.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
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
    latitude: Double?,
    longitude: Double?
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

    @Column(nullable = true)
    var latitude: Double ?= latitude
        private set

    @Column(nullable = true)
    var longitude: Double ?= longitude
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

    fun updateLatitude(latitude: Double?) {
        this.latitude = latitude
    }

    fun updateLongitude(longitude: Double?) {
        this.longitude = longitude
    }
}