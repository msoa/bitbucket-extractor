package com.msoa.extractor.domain.model

data class Location(
    var from: String?,
    var path: String?,
    var to: Int?
) {
    constructor() : this(null, null, null)
}
