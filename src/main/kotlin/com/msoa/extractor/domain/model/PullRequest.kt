package com.msoa.extractor.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.msoa.extractor.infra.bitbucket.client.deserializer.LocalDateTimeDeserializer
import java.time.LocalDateTime

data class PullRequest(
    var description: String?,
    var links: Links?,
    var author: UserRef?,
    @field:JsonProperty("close_source_branch")
    var closeSourceBranch: Boolean = false,
    var title: String?,
    var destination: Source?,
    var reason: String?,
    @field:JsonProperty("closed_by")
    var closedBy: UserRef?,
    var source: Source?,
    var state: String?,
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @field:JsonProperty("created_on")
    var createdOn: LocalDateTime?,
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @field:JsonProperty("updated_on")
    var updatedOn: LocalDateTime?,
    @field:JsonProperty("merge_commit")
    var mergeCommit: CommitRef?,
    var id: Int?,
    @field:JsonProperty("comment_count")
    var commentCount: Int?,
    var summary: Rendered?,
    @field:JsonProperty("task_count")
    var taskCount: Int?,
    var reviewers: List<UserRef>?,
    var participants: List<UserRole>?,
    var type: Type?
) {
    constructor() : this(
        "",
        null,
        null,
        false,
        "",
        null,
        "",
        null,
        null,
        "",
        null,
        null,
        null,
        0,
        0,
        null,
        0,
        null,
        null,
        null
    )
}
