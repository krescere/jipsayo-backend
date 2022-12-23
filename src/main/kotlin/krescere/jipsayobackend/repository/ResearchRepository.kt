package krescere.jipsayobackend.repository

import krescere.jipsayobackend.entity.Research
import org.springframework.data.jpa.repository.JpaRepository

interface ResearchRepository : JpaRepository<Research, Long> {
}