package com.msoa.extractor.infra.persistence.impl

import com.google.gson.Gson
import com.msoa.extractor.domain.model.Commit
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.infra.persistence.entity.CommitEntity
import com.msoa.extractor.infra.persistence.repository.CommitRepository
import org.springframework.stereotype.Component

@Component
class PersistenceCommit(
    private val commitRepository: CommitRepository,
    private val gson: Gson
) {
    fun find(pullRequest: PullRequest): List<Commit> =
        mutableListOf<Commit>().apply {
            pullRequest.let {
                commitRepository.findByRepositoryUuidAndPullRequestId(it.destination?.repository?.uuid!!, it.id!!).forEach {
                    this.add(gson.fromJson(it.json, Commit::class.java))
                }
            }
        }

    fun save(pullRequest: PullRequest, list: List<Commit>) {
        list.forEach {
            pullRequest.apply {
                CommitEntity.build(destination?.repository?.uuid!!, id!!, it, gson).let { ent ->
                    commitRepository.save(ent)
                }
            }
        }
    }
}
