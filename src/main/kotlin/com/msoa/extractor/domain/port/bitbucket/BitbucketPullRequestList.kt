package com.msoa.extractor.domain.port.bitbucket

import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.model.Repository

interface BitbucketPullRequestList {
    fun list(repository: Repository, lastPullRequest: PullRequest?): List<PullRequest>
}
