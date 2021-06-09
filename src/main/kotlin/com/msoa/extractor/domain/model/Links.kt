package com.msoa.extractor.domain.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Links(
    var activity: Link?,
    var aprove: Link?,
    var avatar: Link?,
    var branches: Link?,
    var clone: List<Link>?,
    var code: Link?,
    var comments: Link?,
    var commits: Link?,
    var decline: Link?,
    var diff: Link?,
    var diffstat: Link?,
    var downloads: Link?,
    var forks: Link?,
    var hooks: Link?,
    var html: Link?,
    var issues: Link?,
    var merge: Link?,
    var pullrequests: Link?,
    var requestChanges: Link?,
    var self: Link?,
    var source: Link?,
    var statuses: Link?,
    var tags: Link?,
    var watchers: Link?
) {
    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}
