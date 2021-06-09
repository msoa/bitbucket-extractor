package com.msoa.extractor.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class Approval(
    var date: LocalDateTime?,
    @field:JsonProperty("pullrequest")
    var pullRequest: PullRequestRef?,
    var user: UserRef?
) {
    constructor() : this(null, null, null)
}
