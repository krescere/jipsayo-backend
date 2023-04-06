package krescere.jipsayobackend.common.handler

import krescere.jipsayobackend.dto.dealHistory.LawDealHistory
import krescere.jipsayobackend.dto.house.HouseSaveRequest
import org.springframework.stereotype.Component

@Component
class AddressHandler(
    private val vWordAddressHandler: VWordAddressHandler,
    private val kakaoAddressHandler: KakaoAddressHandler
) {
    fun getHouseSaveRequest(lawDealHistory: LawDealHistory): HouseSaveRequest {
        // VW 를 통해 도로명 주소를 구한다.
        val roadAddress = vWordAddressHandler.getRoadAddress(lawDealHistory)
        // kakao api 를 통해 도로명 주소 -> 구체적인 정보
        val kakaoAddressResponse = kakaoAddressHandler.search(roadAddress)
        // dealHistory to houseSaveRequest
        return HouseSaveRequest(kakaoAddressResponse)
    }
}