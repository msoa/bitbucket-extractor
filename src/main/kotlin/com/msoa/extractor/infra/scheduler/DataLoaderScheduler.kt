package com.msoa.extractor.infra.scheduler

import com.msoa.extractor.domain.usecase.LoadData
import com.msoa.extractor.infra.config.logger
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class DataLoaderScheduler(
    private val loadData: LoadData
) {
    var inProcess = false
    @Scheduled(cron = "*/10 * * * * ?")
    fun executeLoad() {
        logger().info("Scheduler start")
        if (!inProcess) {
            inProcess = true

            try {
                loadData.execute()
            } finally {
                inProcess = false
            }
        }
        logger().info("Scheduler completed")
    }
}
