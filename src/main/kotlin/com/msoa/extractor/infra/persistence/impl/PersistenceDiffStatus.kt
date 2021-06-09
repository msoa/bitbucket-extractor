package com.msoa.extractor.infra.persistence.impl

import com.google.gson.Gson
import com.msoa.extractor.domain.model.DiffStatus
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.infra.persistence.entity.DiffStatusEntity
import com.msoa.extractor.infra.persistence.repository.DiffStatusRepository
import org.springframework.stereotype.Component

@Component
class PersistenceDiffStatus(
    private val repository: DiffStatusRepository,
    private val gson: Gson
) {
    fun find(pullRequest: PullRequest): List<DiffStatus> =
        mutableListOf<DiffStatus>().apply {
            pullRequest.let { pr ->
                repository.findByRepositoryUuidAndPullRequestId(pr.destination?.repository?.uuid!!, pr.id!!).forEach {
                    this.add(gson.fromJson(it.json, DiffStatus::class.java))
                }
            }
        }

    fun save(pullRequest: PullRequest, list: List<DiffStatus>) {
        list.forEach {
            pullRequest.apply {
                DiffStatusEntity.build(destination?.repository?.uuid!!, id!!, it, gson).let { ent ->
                    repository.save(ent)
                }
            }
        }
    }
}
