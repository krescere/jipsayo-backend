package krescere.jipsayobackend.service

import krescere.jipsayobackend.dto.DealHistory
import krescere.jipsayobackend.dto.HouseSaveRequest
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AddressHandler(
    private val vWordAddressHandler: VWordAddressHandler,
    private val kakaoAddressHandler: KakaoAddressHandler
) {
    fun getHouseSaveRequest(dealHistory: DealHistory): HouseSaveRequest{
        // VW 를 통해 도로명 주소를 구한다.
        val roadAddress = vWordAddressHandler.getRoadAddress(dealHistory)
        // kakao api 를 통해 도로명 주소 -> 구체적인 정보
        val kakaoAddressResponse = kakaoAddressHandler.search(roadAddress)
        // dealHistory to houseSaveRequest
        return HouseSaveRequest(
            jibunAddress = kakaoAddressResponse.jibunAddress,
            roadAddress = kakaoAddressResponse.roadAddress,
            cost = dealHistory.cost?.toLong()?:0,
            hangCode = kakaoAddressResponse.hangCode,
            danjiName = kakaoAddressResponse.danjiName,
            postCode = kakaoAddressResponse.postCode,
            latitude = kakaoAddressResponse.latitude,
            longitude = kakaoAddressResponse.longitude,
            dealDate = LocalDateTime.of(
                dealHistory.dealYear?.toInt()?:0,
                dealHistory.dealMonth?.toInt()?:0,
                dealHistory.dealDay?.toInt()?:0,
                0,
                0
            ),
            dedicatedArea = dealHistory.dedicatedArea?.toDouble()?:0.0
        )
    }
}