package com.msoa.extractor.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class RepositoryRef(
    @field:JsonProperty("full_name")
    var fullName: String,
    var links: Links?,
    var name: String,
    var uuid: String,
    var type: Type?
) {
    constructor() : this("", null, "", "", null)
}
