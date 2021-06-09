package com.msoa.extractor.infra.persistence.impl

import com.google.gson.Gson
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.model.Repository
import com.msoa.extractor.domain.port.persistence.PersistencePullRequest
import com.msoa.extractor.infra.persistence.entity.PullRequestEntity
import com.msoa.extractor.infra.persistence.repository.PullRequestRepository
import org.springframework.stereotype.Component

@Component
class PersistencePullRequest(
    private val pullRequestRepository: PullRequestRepository,
    private val gson: Gson
) : PersistencePullRequest {
    override fun findLast(repository: Repository): PullRequest? =
        pullRequestRepository.findFirstByUuidOrderByIdDesc(repository.uuid)?.let {
            gson.fromJson(it.json, PullRequest::class.java)
        }

    override fun find(repository: Repository): List<PullRequest> =
        mutableListOf<PullRequest>().apply {
            pullRequestRepository.findAllByUuid(repository.uuid).forEach {
                this.add(gson.fromJson(it.json, PullRequest::class.java))
            }
        }

    override fun save(list: List<PullRequest>) {
        list.forEach {
            PullRequestEntity.build(it, gson).let { ent ->
                pullRequestRepository.save(ent)
            }
        }
    }
}
