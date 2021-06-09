package com.msoa.extractor.infra.persistence.impl

import com.google.gson.Gson
import com.msoa.extractor.domain.model.Comment
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.infra.persistence.entity.CommentEntity
import com.msoa.extractor.infra.persistence.repository.CommentRepository
import org.springframework.stereotype.Component

@Component
class PersistenceComment(
    private val repository: CommentRepository,
    private val gson: Gson
) {
    fun find(pullRequest: PullRequest): List<Comment> =
        mutableListOf<Comment>().apply {
            pullRequest.let { pr ->
                repository.findByRepositoryUuidAndPullRequestId(pr.destination?.repository?.uuid!!, pr.id!!).forEach {
                    this.add(gson.fromJson(it.json, Comment::class.java))
                }
            }
        }

    fun save(pullRequest: PullRequest, list: List<Comment>) {
        list.forEach {
            pullRequest.apply {
                CommentEntity.build(destination?.repository?.uuid!!, id!!, it, gson).let { ent ->
                    repository.save(ent)
                }
            }
        }
    }
}
