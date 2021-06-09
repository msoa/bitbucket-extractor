package com.msoa.extractor.domain.model

import java.time.LocalDateTime

data class Update(
    var author: UserRef?,
    var changes: Changes?,
    var date: LocalDateTime?,
    var description: String?,
    var destination: Source?,
    var reason: String?,
    var reviewers: List<UserRef>?,
    var source: Source?,
    var state: String?,
    var title: String?
) {
    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}
