package com.msoa.extractor.domain.model

data class ProjectRef(
    var uuid: String?,
    var key: String?,
    var name: String?,
    var links: Links?,
    var type: Type?
) {
    constructor() : this(null, null, null, null, null)
}
