package krescere.jipsayobackend.common

import krescere.jipsayobackend.dto.dealHistory.DealHistorySaveRequest
import krescere.jipsayobackend.service.DealHistoryService
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.time.LocalDateTime

private const val DEFAULT_NUM_OF_ROWS = 100

@Component
class DealHistoryTasklet(
    private val dealHistoryService: DealHistoryService
) : Tasklet {
    val logger = LoggerFactory.getLogger(DealHistoryTasklet::class.java)!!

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        // init
        val pageNo = 1
        val nowYMD = getNowYearMonth()
        val lawdCdList = getLawdCdList()

        for(lawdCd in lawdCdList) {
            saveDealHistory(pageNo, DEFAULT_NUM_OF_ROWS, lawdCd, nowYMD)
        }

        return RepeatStatus.FINISHED
    }
    fun saveDealHistory(parPageNo: Int, numOfRows: Int, LAWD_CD: String, DEAL_YMD: String) {
        var pageNo = parPageNo
        var totalCount = numOfRows+1
        while(totalCount > pageNo * numOfRows) {
            try {
                totalCount = dealHistoryService.save(
                    DealHistorySaveRequest(
                        pageNo = pageNo,
                        numOfRows = numOfRows,
                        lawdCd = LAWD_CD,
                        dearYmd = DEAL_YMD
                    )
                )
            } catch (e: Exception) {
                logger.error("saveDealHistory error: $e")
            }
            pageNo++
        }
    }

    fun getLawdCdList() : List<String> {
        val resource=ClassPathResource("LAWD_CD.txt")
        val br=resource.inputStream.bufferedReader()
        val lawdCdList = mutableListOf<String>()
        while(true) {
            val line = br.readLine() ?: break
            lawdCdList.add(line)
        }
        return lawdCdList
    }
    fun getNowYearMonth() : String {
        val now = LocalDateTime.now()
        val year = now.year
        var month = now.monthValue.toString()
        if(month.length == 1) month = "0$month"
        return "$year$month"
    }
}