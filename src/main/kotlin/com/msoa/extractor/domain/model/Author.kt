package com.msoa.extractor.domain.model

data class Author(
    var raw: String?,
    var user: UserRef?,
    var type: Type?
) {
    constructor() : this(null, null, null)
}
