package krescere.jipsayobackend.repository

import krescere.jipsayobackend.entity.Deal
import org.springframework.data.jpa.repository.JpaRepository

interface DealRepository : JpaRepository<Deal, Long> {
}