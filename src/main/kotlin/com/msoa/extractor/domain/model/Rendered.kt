package com.msoa.extractor.domain.model

data class Rendered(
    var raw: String?,
    var markup: String?,
    var html: String?,
    var type: Type?
) {
    constructor() : this(null, null, null, null)
}
