package krescere.jipsayobackend.dto

class HouseGetQuery(
    id: Long?,
    roadAddress: String?,
    danjiName: String?
) {
    var id: Long ?= id
        private set
    var roadAddress: String ?= underScoreToSpace(roadAddress)
        private set
    var danjiName: String ?= underScoreToSpace(danjiName)
        private set

    // 언더바를 띄어쓰기로 바꿔주는 함수
    private fun underScoreToSpace(str: String?) : String? {
        return str?.replace("_", " ")
    }
}