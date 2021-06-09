package com.msoa.extractor.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class Repository(
    var scm: String = "",
    var website: String?,
    @field:JsonProperty("has_wiki")
    var hasWiki: Boolean = false,
    var uuid: String = "",
    var links: Links?,
    @field:JsonProperty("fork_policy")
    var forkPolicy: String = "",
    @field:JsonProperty("full_name")
    var fullName: String = "",
    var name: String = "",
    var project: ProjectRef?,
    var language: String = "",
    @field:JsonProperty("created_on")
    var createdOn: LocalDateTime?,
    @field:JsonProperty("mainbranch")
    var mainBranch: NamedBranch?,
    var workspace: Workspace?,
    @field:JsonProperty("has_issues")
    var hasIssues: Boolean = false,
    var owner: UserRef?,
    @field:JsonProperty("updated_on")
    var updatedOn: LocalDateTime?,
    var size: Long = 0,
    var slug: String = "",
    @field:JsonProperty("is_private")
    var isPrivate: Boolean = false,
    var description: String = "",
    var parent: RepositoryRef?,
    var type: Type
) {
    constructor() : this(
        "",
        "",
        false,
        "",
        null,
        "",
        "",
        "",
        null,
        "",
        null,
        null,
        null,
        false,
        null,
        null,
        0,
        "",
        false,
        "",
        null,
        Type.REPOSITORY
    )
}
