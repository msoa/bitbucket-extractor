package com.msoa.extractor.infra.bitbucket.impl

import com.msoa.extractor.domain.model.Commit
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.port.bitbucket.RequestCallback
import com.msoa.extractor.domain.port.persistence.PersistenceLogRequest
import com.msoa.extractor.infra.bitbucket.AbstractBitbucket
import com.msoa.extractor.infra.bitbucket.client.BitbucketClient
import org.springframework.stereotype.Service

@Service
class BitbucketCommitImpl(
    client: BitbucketClient<Commit>,
    requestCallback: RequestCallback,
    persistenceLogRequest: PersistenceLogRequest
) : AbstractBitbucket<Commit>(client, requestCallback, persistenceLogRequest) {

    fun list(pullRequest: PullRequest): List<Commit> =
        mutableListOf<Commit>().apply {
            endpoint(
                baseUrl = pullRequest.links?.commits?.href!!,
                sortExp = "date"
            ).let {
                if (notCalled(it))
                    addAll(
                        list(it, Commit::class.java)
                    )
            }

            endpoint(
                baseUrl = pullRequest.links?.self?.href!!,
                sortExp = "date"
            ).let {
                if (notCalled(it))
                    addAll(
                        list(it, Commit::class.java)
                    )
            }
        }
}
