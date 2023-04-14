package krescere.jipsayobackend.common.handler

import com.google.gson.Gson
import krescere.jipsayobackend.dto.dealHistory.DealHistoryFilterRequest
import krescere.jipsayobackend.dto.house.HousePredictResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class PredictHandler(
    private val httpHandler: ApacheHttpHandler,
    private val gson: Gson
) {
    val logger = LoggerFactory.getLogger(this.javaClass)!!
    fun getCandidateMap(request: DealHistoryFilterRequest) : Map<Long, HousePredictResponse> {
        return try {
            val candidateJson = getCandidateJson(request)
            val candidateList = gson.fromJson(candidateJson, Array<HousePredictResponse>::class.java).toList()
            candidateList.associateBy { it.id }
        } catch (e: Exception) {
            logger.error("getCandidateMap error", e)
            emptyMap()
        }
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

    fun reload() {
        val url="http://localhost:5000/api/v1/houses/reload"
        httpHandler.get(url)
    }
}