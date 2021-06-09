package com.msoa.extractor.infra.bitbucket

import com.msoa.extractor.domain.model.Activity
import com.msoa.extractor.domain.model.Comment
import com.msoa.extractor.domain.model.Commit
import com.msoa.extractor.domain.model.DiffStatus
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.port.bitbucket.BitbucketList
import com.msoa.extractor.infra.bitbucket.impl.BitbucketActivityImpl
import com.msoa.extractor.infra.bitbucket.impl.BitbucketCommentImpl
import com.msoa.extractor.infra.bitbucket.impl.BitbucketCommitImpl
import com.msoa.extractor.infra.bitbucket.impl.BitbucketDiffStatusImpl
import org.springframework.stereotype.Service

@Service
class BitbucketDelegate<T>(
    val bitbucketActivityImpl: BitbucketActivityImpl,
    val bitbucketCommentImpl: BitbucketCommentImpl,
    val bitbucketCommitImpl: BitbucketCommitImpl,
    val bitbucketDiffStatusImpl: BitbucketDiffStatusImpl
) : BitbucketList<T> {
    @Suppress("UNCHECKED_CAST")
    override fun list(pullRequest: PullRequest, classOfResult: Class<T>): List<T> =
        when (classOfResult) {
            Activity::class.java -> bitbucketActivityImpl.list(pullRequest) as List<T>
            Comment::class.java -> bitbucketCommentImpl.list(pullRequest) as List<T>
            Commit::class.java -> bitbucketCommitImpl.list(pullRequest) as List<T>
            DiffStatus::class.java -> bitbucketDiffStatusImpl.list(pullRequest) as List<T>
            else -> throw Exception("Invalid document type")
        }
}
