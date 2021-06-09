package com.msoa.extractor.domain.model

data class CommentInfo(
    var id: Long?,
    var links: Links?
) {
    constructor() : this(null, null)
}
