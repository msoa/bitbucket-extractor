package com.msoa.extractor.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class DiffStatus(
    var status: String?,
    @field:JsonProperty("lines_removed")
    var linesRemoved: Int?,
    @field:JsonProperty("lines_added")
    var linesAdded: Int?,
    var old: DiffAction?,
    var new: DiffAction?,
    var type: String?
) {
    constructor() : this(null, 0, 0, null, null, null)
}
