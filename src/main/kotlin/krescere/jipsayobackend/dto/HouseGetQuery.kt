package krescere.jipsayobackend.dto

class HouseGetQuery(
    id: Long?,
    jibunAddress: String?,
    roadAddress: String?
) {
    var id: Long ?= id
        private set
    var jibunAddress: String ?= underScoreToSpace(jibunAddress)
        private set
    var roadAddress: String ?= underScoreToSpace(roadAddress)
        private set

    // 언더바를 띄어쓰기로 바꿔주는 함수
    private fun underScoreToSpace(str: String?) : String? {
        return str?.replace("_", " ")
    }
}