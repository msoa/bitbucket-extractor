package com.msoa.extractor.infra.bitbucket.impl

import com.msoa.extractor.domain.model.Activity
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.port.bitbucket.RequestCallback
import com.msoa.extractor.domain.port.persistence.PersistenceLogRequest
import com.msoa.extractor.infra.bitbucket.AbstractBitbucket
import com.msoa.extractor.infra.bitbucket.client.BitbucketClient
import org.springframework.stereotype.Service

@Service
class BitbucketActivityImpl(
    client: BitbucketClient<Activity>,
    requestCallback: RequestCallback,
    persistenceLogRequest: PersistenceLogRequest
) : AbstractBitbucket<Activity>(client, requestCallback, persistenceLogRequest) {

    fun list(pullRequest: PullRequest): List<Activity> =
        mutableListOf<Activity>().apply {
            endpoint(
                baseUrl = pullRequest.links?.activity?.href!!,
                sortExp = "created_on"
            ).let {
                if (notCalled(it))
                    addAll(
                        list(it, Activity::class.java)
                    )
            }
        }
}
