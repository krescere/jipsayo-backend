package krescere.jipsayobackend.common

import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class DealHistoryTasklet : Tasklet {
    val logger = LoggerFactory.getLogger(DealHistoryTasklet::class.java)!!
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        logger.info("DealHistoryTasklet")
        return RepeatStatus.FINISHED
    }
}