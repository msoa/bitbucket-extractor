package com.msoa.extractor.domain.model

data class Link(
    var href: String?,
    var name: String?
) {
    constructor() : this(null, null)
}
