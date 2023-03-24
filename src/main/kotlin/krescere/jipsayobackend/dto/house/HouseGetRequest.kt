package krescere.jipsayobackend.dto.house

class HouseGetRequest(
    id: Long?,
    roadAddress: String?,
    danjiName: String?
) {
    val id: Long ?= id
    val roadAddress: String ?= underScoreToSpace(roadAddress)
    val danjiName: String ?= underScoreToSpace(danjiName)

    // 언더바를 띄어쓰기로 바꿔주는 함수
    private fun underScoreToSpace(str: String?) : String? {
        return str?.replace("_", " ")
    }
}