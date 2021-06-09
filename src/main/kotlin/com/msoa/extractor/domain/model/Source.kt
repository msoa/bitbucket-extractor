package com.msoa.extractor.domain.model

data class Source(
    var commit: CommitRef?,
    var repository: RepositoryRef?,
    var branch: NamedBranch?
) {
    constructor() : this(null, null, null)
}
