package krescere.jipsayobackend.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@EntityListeners(AuditingEntityListener::class)
@Table(name = "house")
@Entity
class House(
    jibunAddress: String,
    roadAddress: String,
    cost: Long,
    latitude: Double?,
    longitude: Double?
) {
    @Column(nullable = false, unique = true)
    var jibunAddress: String = jibunAddress
        private set

    @Column(nullable = false, unique = true)
    var roadAddress: String = roadAddress
        private set

    @Column(nullable = false)
    var cost: Long = cost
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

    fun updateLatitude(latitude: Double?) {
        this.latitude = latitude
    }

    fun updateLongitude(longitude: Double?) {
        this.longitude = longitude
    }
}