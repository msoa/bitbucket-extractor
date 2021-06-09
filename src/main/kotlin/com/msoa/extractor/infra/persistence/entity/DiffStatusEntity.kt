package com.msoa.extractor.infra.persistence.entity

import com.google.gson.Gson
import com.msoa.extractor.domain.model.DiffStatus
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "diff_status")
class DiffStatusEntity(
    @Id
    @Column(name = "hash")
    var hash: String?,
    @Column(name = "pull_request_id")
    var pullRequestId: Int?,
    @Column(name = "repository_uuid")
    var repositoryUuid: String?,
    @Column(name = "status")
    var status: String?,
    @Column(name = "lines_added")
    var linesAdded: Int?,
    @Column(name = "lines_removed")
    var linesRemoved: Int?,
    @Column(name = "json")
    var json: String?
) {
    companion object {
        fun build(repositoryUuid: String, pullRequestId: Int, diffStatus: DiffStatus, gson: Gson): DiffStatusEntity {
            diffStatus.apply {
                gson.toJson(this).let { json ->
                    return DiffStatusEntity(
                        hash = HashHelper.md5Hash(json),
                        repositoryUuid = repositoryUuid,
                        pullRequestId = pullRequestId,
                        status = status,
                        linesAdded = linesAdded,
                        linesRemoved = linesRemoved,
                        json = json
                    )
                }
            }
        }
    }
}
