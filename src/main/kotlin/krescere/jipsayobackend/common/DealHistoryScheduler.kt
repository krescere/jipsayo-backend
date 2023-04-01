package krescere.jipsayobackend.common

import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class DealHistoryScheduler(
    private val dealHistoryJob: Job,
    private val jobLauncher: JobLauncher
) {
    val logger = LoggerFactory.getLogger(DealHistoryScheduler::class.java)!!
    @Scheduled(cron = "0 0 0 1 1/1 ?") // 매월 1일 0시 0분 0초
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