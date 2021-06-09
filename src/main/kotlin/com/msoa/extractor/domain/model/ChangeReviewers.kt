package com.msoa.extractor.domain.model

data class ChangeReviewers(
    var added: List<UserRef>?,
    var new: List<UserRef>?,
    var removed: List<UserRef>?
) {
    constructor() : this(null, null, null)
}
