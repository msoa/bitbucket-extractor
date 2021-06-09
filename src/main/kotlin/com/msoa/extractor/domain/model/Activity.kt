package com.msoa.extractor.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Activity(
    @field:JsonProperty("pull_request")
    var pullRequest: PullRequestRef?,
    var update: Update?,
    var approval: Approval?,
    var comment: Comment?
) {
    constructor() : this(null, null, null, null)
}
