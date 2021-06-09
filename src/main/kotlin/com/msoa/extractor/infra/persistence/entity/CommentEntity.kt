package com.msoa.extractor.infra.persistence.entity

import com.google.gson.Gson
import com.msoa.extractor.domain.model.Comment
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "comment")
class CommentEntity(
    @Id
    @Column(name = "id")
    var id: Long?,
    @Column(name = "pull_request_id")
    var pullRequestId: Int?,
    @Column(name = "repository_uuid")
    var repositoryUuid: String?,
    @Column(name = "create_on")
    var createOn: LocalDateTime?,
    @Column(name = "json")
    var json: String?
) {
    companion object {
        fun build(repositoryUuid: String, pullRequestId: Int, comment: Comment, gson: Gson) = comment.let {
            CommentEntity(
                id = it.id,
                repositoryUuid = repositoryUuid,
                pullRequestId = pullRequestId,
                createOn = it.createdOn,
                json = gson.toJson(it)
            )
        }
    }
}
