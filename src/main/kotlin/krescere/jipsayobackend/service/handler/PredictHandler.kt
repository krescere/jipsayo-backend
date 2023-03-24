package krescere.jipsayobackend.service.handler

import com.google.gson.Gson
import krescere.jipsayobackend.dto.HousePredictResponse
import krescere.jipsayobackend.dto.dealHistory.DealHistoryFilterRequest
import org.springframework.stereotype.Component
@Component
class PredictHandler(
    private val httpHandler: ApacheHttpHandler,
    private val gson: Gson
) {
    fun getCandidateMap(request: DealHistoryFilterRequest) : Map<Long, HousePredictResponse> {
        val candidateJson = getCandidateJson(request)
        val candidateList = gson.fromJson(candidateJson, Array<HousePredictResponse>::class.java).toList()
        return candidateList.associateBy { it.id }
    }
    private fun getCandidateJson(request: DealHistoryFilterRequest) : String {
        val urlBuilder = StringBuilder()
        val baseURL="http://localhost:5000/api/v1/houses/filter"

        urlBuilder.append(baseURL)
        urlBuilder.append("?latitude=${request.latitude}")
        urlBuilder.append("&longitude=${request.longitude}")
        urlBuilder.append("&time=${request.time}")

        return httpHandler.get(urlBuilder.toString()) ?: ""
    }
}