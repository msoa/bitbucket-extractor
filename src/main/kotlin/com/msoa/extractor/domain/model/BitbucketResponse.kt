package com.msoa.extractor.domain.model

open class BitbucketResponse<T> (
    var pagelen: Int,
    var size: Int,
    var values: List<T>?,
    var page: Int,
    var next: String?
) {
    constructor() : this(0, 0, null, 0, null)
}
