package krescere.jipsayobackend.common

import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DealHistoryScheduler(
    private val dealHistoryJob: Job,
    private val jobLauncher: JobLauncher
) {
    val logger = LoggerFactory.getLogger(DealHistoryScheduler::class.java)!!
    @Scheduled(fixedDelay = 5*1000L)
    fun executeJob() {
        val jobParameters = JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters()
        try {
            jobLauncher.run(dealHistoryJob, jobParameters)
        } catch (e: Exception) {
            logger.error(e.message)
        }
    }
}