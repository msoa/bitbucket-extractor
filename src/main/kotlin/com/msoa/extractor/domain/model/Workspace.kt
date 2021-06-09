package com.msoa.extractor.domain.model

data class Workspace(
    var slug: String?,
    var name: String?,
    var links: Links?,
    var uuid: String?,
    var type: Type?
) {
    constructor() : this(null, null, null, null, null)
}
