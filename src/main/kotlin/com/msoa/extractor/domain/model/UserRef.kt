package com.msoa.extractor.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UserRef(
    @field:JsonProperty("account_id")
    var accountId: String?,
    var uuid: String,
    var username: String?,
    var nickname: String?,
    @field:JsonProperty("display_name")
    var displayName: String,
    var links: Links?,
    var type: Type?
) {
    constructor() : this(null, "", null, null, "", null, null)
}
