package com.msoa.extractor.infra.persistence.impl

import com.google.gson.Gson
import com.msoa.extractor.domain.model.Activity
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.infra.persistence.entity.ActivityEntity
import com.msoa.extractor.infra.persistence.repository.ActivityRepository
import org.springframework.stereotype.Component

@Component
class PersistenceActivity(
    private val repository: ActivityRepository,
    private val gson: Gson
) {
    fun find(pullRequest: PullRequest): List<Activity> =
        mutableListOf<Activity>().apply {
            pullRequest.let { pr ->
                repository.findByRepositoryUuidAndPullRequestId(pr.destination?.repository?.uuid!!, pr.id!!).forEach {
                    this.add(gson.fromJson(it.json, Activity::class.java))
                }
            }
        }

    fun save(pullRequest: PullRequest, list: List<Activity>) {
        list.forEach {
            pullRequest.apply {
                ActivityEntity.build(destination?.repository?.uuid!!, id!!, it, gson).let { ent ->
                    repository.save(ent)
                }
            }
        }
    }
}
