package com.msoa.extractor.domain.port.bitbucket

import com.msoa.extractor.domain.model.PullRequest

interface BitbucketList<T> {
    fun list(pullRequest: PullRequest, classOfResult: Class<T>): List<T>
}
