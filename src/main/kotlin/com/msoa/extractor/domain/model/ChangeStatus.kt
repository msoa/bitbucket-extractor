package com.msoa.extractor.domain.model

data class ChangeStatus(
    var new: String?,
    var old: String?
) {
    constructor() : this(null, null)
}
