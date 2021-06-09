package com.msoa.extractor.domain.model

enum class Type(val description: String) {
    AUTHOR("author"),
    BRANCH("branch"),
    COMMIT("commit"),
    DIFF_STATUS("diffstatus"),
    PROJECT("project"),
    PULL_REQUEST("pullrequest"),
    PULL_REQUEST_COMMENT("pullrequest_comment"),
    RENDERED("rendered"),
    REPOSITORY("repository"),
    TEAM("team"),
    USER("user"),
    WORKSPACE("workspace");

    companion object {
        fun create(description: String): Type = values().first { it.description == description }
    }
}
