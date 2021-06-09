package com.msoa.extractor.domain.usecase

import com.msoa.extractor.infra.config.logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class LoadData(
    private val listRespositories: ListRespositories,
    private val listPullRequests: ListPullRequests,
    private val listCommits: ListCommits,
    private val listComments: ListComments,
    private val listActivities: ListActivities,
    private val listDiffStatus: ListDiffStatuses,
    @Value("\${bitbucket.project.name}") val projectName: String? = null
) {
    fun execute() {
        logger().info("Data load starting")
        listRespositories.execute(projectName).forEach { repo ->
            logger().info("Repository ${repo.fullName} loaded")
            listPullRequests.execute(repo).forEach {
                listCommits.execute(it)
                listComments.execute(it)
                listActivities.execute(it)
                listDiffStatus.execute(it)
            }
        }
        logger().info("Data load completed")
    }
}
