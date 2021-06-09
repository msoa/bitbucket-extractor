package com.msoa.extractor.domain.model

data class NamedBranch(
    var name: String?,
    var type: Type?
) {
    constructor() : this(null, null)
}
