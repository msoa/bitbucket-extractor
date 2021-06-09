package com.msoa.extractor.domain.model

data class RenderedMessage(
    var message: Rendered?
) {
    constructor() : this(null)
}
