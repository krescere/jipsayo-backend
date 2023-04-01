package krescere.jipsayobackend.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@ConditionalOnProperty(
    value = ["app.scheduling.enable"],
    havingValue = "true",
    matchIfMissing = true
)
@Configuration
class SchedulerConfig {
}