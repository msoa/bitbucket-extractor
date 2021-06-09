package com.msoa.extractor.domain.model

data class UserRole(
    var user: UserRef?,
    var role: String?,
    var approved: String?
) {
    constructor() : this(null, null, null)
}
