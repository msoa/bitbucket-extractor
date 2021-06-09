package com.msoa.extractor.domain.model

data class Changes(
    var status: ChangeStatus?,
    var description: ChangeStatus?,
    var reviewers: ChangeReviewers?,
    var title: ChangeStatus?
) {
    constructor() : this(null, null, null, null)
}
