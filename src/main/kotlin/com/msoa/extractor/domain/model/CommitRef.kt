package com.msoa.extractor.domain.model

data class CommitRef(
    var hash: String?,
    var links: Links?,
    var type: Type?
) {
    constructor() : this(null, null, null)
}
