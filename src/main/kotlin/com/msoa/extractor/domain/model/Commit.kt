package com.msoa.extractor.domain.model

import java.time.LocalDateTime

data class Commit(
    var author: Author?,
    var date: LocalDateTime?,
    var hash: String?,
    var links: Links?,
    var message: String?,
    var parents: List<CommitRef>?,
    var rendered: RenderedMessage?,
    var repository: RepositoryRef?,
    var summary: Rendered?,
    var participants: List<UserRef>?,
    var type: Type?
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
        null,
        null
    )
}
