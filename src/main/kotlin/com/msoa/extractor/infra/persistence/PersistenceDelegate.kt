package com.msoa.extractor.infra.persistence

import com.msoa.extractor.domain.model.Activity
import com.msoa.extractor.domain.model.Comment
import com.msoa.extractor.domain.model.Commit
import com.msoa.extractor.domain.model.DiffStatus
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.port.persistence.Persistence
import com.msoa.extractor.infra.persistence.impl.PersistenceActivity
import com.msoa.extractor.infra.persistence.impl.PersistenceComment
import com.msoa.extractor.infra.persistence.impl.PersistenceCommit
import com.msoa.extractor.infra.persistence.impl.PersistenceDiffStatus
import org.springframework.stereotype.Service

@Service
class PersistenceDelegate<T>(
    val persistenceActivity: PersistenceActivity,
    val persistenceComment: PersistenceComment,
    val persistenceCommit: PersistenceCommit,
    val persistenceDiffStatus: PersistenceDiffStatus
) : Persistence<T> {
    @Suppress("UNCHECKED_CAST")
    override fun find(pullRequest: PullRequest, classOfResult: Class<T>): List<T> =
        when (classOfResult) {
            Activity::class.java -> persistenceActivity.find(pullRequest) as List<T>
            Comment::class.java -> persistenceComment.find(pullRequest) as List<T>
            Commit::class.java -> persistenceCommit.find(pullRequest) as List<T>
            DiffStatus::class.java -> persistenceDiffStatus.find(pullRequest) as List<T>
            else -> throw Exception("Invalid type")
        }

    @Suppress("UNCHECKED_CAST")
    override fun save(pullRequest: PullRequest, list: List<T>, classOfResult: Class<T>) {
        when (classOfResult) {
            Activity::class.java -> persistenceActivity.save(pullRequest, list as List<Activity>)
            Comment::class.java -> persistenceComment.save(pullRequest, list as List<Comment>)
            Commit::class.java -> persistenceCommit.save(pullRequest, list as List<Commit>)
            DiffStatus::class.java -> persistenceDiffStatus.save(pullRequest, list as List<DiffStatus>)
            else -> throw Exception("Invalid type")
        }
    }
}
