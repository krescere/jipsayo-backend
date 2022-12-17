package krescere.jipsayobackend.entity

import javax.persistence.*

@Entity
class House(
    jibunAddress: String,
    cost: Long,
    latitude: Double,
    longitude: Double
) {
    @Column(nullable = false, unique = true)
    var jibunAddress: String = jibunAddress
        private set

    @Column(nullable = false)
    var cost: Long = cost
        private set

    @Column(nullable = false)
    var latitude: Double = latitude
        private set

    @Column(nullable = false)
    var longitude: Double = longitude
        private set

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}