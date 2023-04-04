package krescere.jipsayobackend.repository

import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import krescere.jipsayobackend.dto.deal.DealFilterResponse
import krescere.jipsayobackend.entity.QDeal
import krescere.jipsayobackend.entity.QHouse
import krescere.jipsayobackend.entity.QHouseDetail
import org.springframework.stereotype.Repository
import java.util.stream.Stream

@Repository
class DealQueryDSLRepository(
    private val queryFactory: JPAQueryFactory,
) {
    fun findDealsByCostRange(highCost: Long, lowCost: Long): Stream<DealFilterResponse> {
        val qDeal = QDeal.deal
        val qHouseDetail = QHouseDetail.houseDetail
        val qHouse = QHouse.house

        return queryFactory.select(
            Projections.constructor(
                DealFilterResponse::class.java,
                qHouse.id,
                qHouse.roadAddress,
                qHouse.danjiName,
                qDeal.cost,
                qHouse.latitude,
                qHouse.longitude,
                qDeal.dealDate,
                qHouseDetail.dedicatedArea
            )
        ).from(qDeal)
            .join(qDeal.houseDetail, qHouseDetail)
            .join(qHouseDetail.house, qHouse)
            .where(qDeal.cost.between(lowCost, highCost))
            .orderBy(qHouse.id.asc(), qDeal.dealDate.desc())
            .distinct()
            .fetch().stream()
    }
}