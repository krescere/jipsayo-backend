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
        val deal = QDeal.deal
        val houseDetail = QHouseDetail.houseDetail
        val house = QHouse.house

        val deal2 = QDeal.deal
        val house2 = QHouse.house

        return queryFactory.select(
            Projections.constructor(DealFilterResponse::class.java,
            house.id,
            house.roadAddress,
            house.danjiName,
            deal.cost,
            house.latitude,
            house.longitude,
            deal.dealDate,
            houseDetail.dedicatedArea))
            .from(deal)
            .join(deal.houseDetail, houseDetail).fetchJoin()
            .join(houseDetail.house, house).fetchJoin()
            .where(deal.cost.lt(highCost), deal.cost.gt(lowCost),
                deal.dealDate.eq(
                    JPAExpressions.select(deal2.dealDate)
                    .from(deal2)
                    .join(deal2.houseDetail, houseDetail)
                    .join(houseDetail.house, house2)
                    .where(house2.roadAddress.eq(house.roadAddress),
                        house2.danjiName.eq(house.danjiName))))
            .groupBy(house.roadAddress, house.danjiName)
            .fetch().stream()
    }
}