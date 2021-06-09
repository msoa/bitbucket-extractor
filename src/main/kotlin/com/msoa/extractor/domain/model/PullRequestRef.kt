package com.msoa.extractor.domain.model

data class PullRequestRef(
    var id: Int?,
    var links: Links?,
    var title: String?,
    var type: Type?
) {
    constructor() : this(null, null, null, null)
}
