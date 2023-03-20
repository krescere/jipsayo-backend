package krescere.jipsayobackend.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
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
    latitude: Double,
    longitude: Double,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}