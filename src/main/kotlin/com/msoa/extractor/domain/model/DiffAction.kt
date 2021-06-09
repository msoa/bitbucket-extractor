package com.msoa.extractor.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class DiffAction(
    var path: String?,
    @field:JsonProperty("escaped_path")
    var escapedPath: String?,
    var type: String?,
    var links: Links?
) {
    constructor() : this(null, null, null, null)
}
