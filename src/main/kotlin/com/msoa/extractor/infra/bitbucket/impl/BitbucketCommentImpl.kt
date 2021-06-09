package com.msoa.extractor.infra.bitbucket.impl

import com.msoa.extractor.domain.model.Comment
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.port.bitbucket.RequestCallback
import com.msoa.extractor.domain.port.persistence.PersistenceLogRequest
import com.msoa.extractor.infra.bitbucket.AbstractBitbucket
import com.msoa.extractor.infra.bitbucket.client.BitbucketClient
import org.springframework.stereotype.Service

@Service
class BitbucketCommentImpl(
    client: BitbucketClient<Comment>,
    requestCallback: RequestCallback,
    persistenceLogRequest: PersistenceLogRequest
) : AbstractBitbucket<Comment>(client, requestCallback, persistenceLogRequest) {

    fun list(pullRequest: PullRequest): List<Comment> =
        mutableListOf<Comment>().apply {
            endpoint(
                baseUrl = pullRequest.links?.comments?.href!!,
                sortExp = "created_on"
            ).let {
                if (notCalled(it))
                    addAll(
                        list(it, Comment::class.java)
                    )
            }
        }
}
