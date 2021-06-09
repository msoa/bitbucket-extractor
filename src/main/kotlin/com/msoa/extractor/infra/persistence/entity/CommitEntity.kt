package com.msoa.extractor.infra.persistence.entity

import com.google.gson.Gson
import com.msoa.extractor.domain.model.Commit
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Embeddable
class CommitPK(
    @Column(name = "hash")
    var hash: String?,
    @Column(name = "repository_uuid")
    var repositoryUuid: String?,
    @Column(name = "pull_request_id")
    var pullRequestId: Int?
) : Serializable

@Entity
@Table(name = "commit")
class CommitEntity(
    @EmbeddedId
    var commitPK: CommitPK? = null,
    @Column(name = "message")
    var message: String?,
    @Column(name = "date")
    var date: LocalDateTime?,
    @Column(name = "json")
    var json: String?
) {
    companion object {
        fun build(repositoryUuid: String, pullRequestId: Int, commit: Commit, gson: Gson) = commit.let {
            CommitEntity(
                commitPK = CommitPK(it.hash, repositoryUuid, pullRequestId),
                message = it.message,
                date = it.date,
                json = gson.toJson(it)
            )
        }
    }
}
