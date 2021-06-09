package com.msoa.extractor.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class Comment(
    var parent: CommentInfo?,
    var links: Links?,
    var content: Rendered?,
    @field:JsonProperty("created_on")
    var createdOn: LocalDateTime?,
    var user: UserRef?,
    @field:JsonProperty("updated_on")
    var updatedOn: LocalDateTime?,
    var id: Long?,
    var deleted: Boolean?,
    var inline: Location?,
    @field:JsonProperty("pullrequest")
    var pullRequest: PullRequestRef?,
    var type: Type?
) {
    constructor() : this(null, null, null, null, null, null, null, false, null, null, null)
}
