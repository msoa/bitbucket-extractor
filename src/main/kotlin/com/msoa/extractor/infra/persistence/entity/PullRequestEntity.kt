package com.msoa.extractor.infra.persistence.entity

import com.google.gson.Gson
import com.msoa.extractor.domain.model.PullRequest
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Embeddable
class PullRequestPK(
    @Column(name = "repository_uuid")
    var repositoryUuid: String?,
    @Column(name = "id")
    var id: Int?
) : Serializable

@Entity
@Table(name = "pull_request")
class PullRequestEntity(
    @EmbeddedId
    var pullRequestPK: PullRequestPK? = null,
    @Column(name = "title")
    var title: String?,
    @Column(name = "create_on")
    var createOn: LocalDateTime?,
    @Column(name = "updated_on")
    var updatedOn: LocalDateTime?,
    @Column(name = "json")
    var json: String?
) {
    companion object {
        fun build(pullRequest: PullRequest, gson: Gson) = pullRequest.let {
            PullRequestEntity(
                pullRequestPK = PullRequestPK(it.source?.repository?.uuid, it.id),
                title = it.title,
                createOn = it.createdOn,
                updatedOn = it.updatedOn,
                json = gson.toJson(it)
            )
        }
    }
}
