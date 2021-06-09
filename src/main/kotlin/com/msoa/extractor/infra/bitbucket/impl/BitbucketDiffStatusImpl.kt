package com.msoa.extractor.infra.bitbucket.impl

import com.msoa.extractor.domain.model.DiffStatus
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.port.bitbucket.RequestCallback
import com.msoa.extractor.domain.port.persistence.PersistenceLogRequest
import com.msoa.extractor.infra.bitbucket.AbstractBitbucket
import com.msoa.extractor.infra.bitbucket.client.BitbucketClient
import org.springframework.stereotype.Service

@Service
class BitbucketDiffStatusImpl(
    client: BitbucketClient<DiffStatus>,
    requestCallback: RequestCallback,
    persistenceLogRequest: PersistenceLogRequest
) : AbstractBitbucket<DiffStatus>(client, requestCallback, persistenceLogRequest) {

    fun list(pullRequest: PullRequest): List<DiffStatus> =
        mutableListOf<DiffStatus>().apply {
            endpoint(
                baseUrl = pullRequest.links?.diffstat?.href!!
            ).let {
                if (notCalled(it))
                    addAll(
                        list(it, DiffStatus::class.java)
                    )
            }
        }
}
