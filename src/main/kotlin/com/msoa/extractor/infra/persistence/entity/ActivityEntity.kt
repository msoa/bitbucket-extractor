package com.msoa.extractor.infra.persistence.entity

import com.google.gson.Gson
import com.msoa.extractor.domain.model.Activity
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "activity")
class ActivityEntity(
    @Id
    @Column(name = "hash")
    var hash: String?,
    @Column(name = "pull_request_id")
    var pullRequestId: Int?,
    @Column(name = "repository_uuid")
    var repositoryUuid: String?,
    @Column(name = "date")
    var date: LocalDateTime?,
    @Column(name = "type")
    var type: ActivityType?,
    @Column(name = "json")
    var json: String?
) {
    companion object {
        fun build(repositoryUuid: String, pullRequestId: Int, activity: Activity, gson: Gson): ActivityEntity {
            activity.apply {
                var date: LocalDateTime? = null
                var type: ActivityType? = null

                when {
                    approval?.date != null -> {
                        date = approval?.date!!
                        type = ActivityType.APPROVAL
                    }
                    comment?.createdOn != null -> {
                        date = comment?.createdOn!!
                        type = ActivityType.valueOf(comment?.type!!.name)
                    }
                    update?.date != null -> {
                        date = update?.date!!
                        type = ActivityType.valueOf(update?.state!!)
                    }
                }
                gson.toJson(this).let { json ->
                    return ActivityEntity(
                        hash = HashHelper.md5Hash(json),
                        repositoryUuid = repositoryUuid,
                        pullRequestId = pullRequestId,
                        date = date,
                        type = type,
                        json = json
                    )
                }
            }
        }
    }
}
