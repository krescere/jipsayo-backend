package krescere.jipsayobackend.config

import krescere.jipsayobackend.common.DealHistoryTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableBatchProcessing
class JobConfig {
    @Autowired
    lateinit var jobBuilderFactory: JobBuilderFactory
    @Autowired
    lateinit var stepBuilderFactory: StepBuilderFactory
    @Autowired
    lateinit var dealHistoryTasklet: DealHistoryTasklet


    @Bean
    fun dealHistoryJob(): Job = jobBuilderFactory.get("dealHistoryJob")
        .start(dealHistoryStep())
        .build()

    @Bean
    fun dealHistoryStep(): Step = stepBuilderFactory.get("dealHistoryStep")
        .tasklet(dealHistoryTasklet)
        .build()
}