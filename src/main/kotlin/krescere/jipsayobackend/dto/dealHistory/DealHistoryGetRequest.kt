package krescere.jipsayobackend.dto.dealHistory

class DealHistoryGetRequest(
    roadAddress: String,
    danjiName: String,
) {
    val roadAddress: String = underToSpace(roadAddress)
    val danjiName: String = underToSpace(danjiName)

    // underscore to space
    private fun underToSpace(s:String) : String {
        return s.replace("_", " ")
    }
}