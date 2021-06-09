package com.msoa.extractor.infra.bitbucket.impl

import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.model.Repository
import com.msoa.extractor.domain.port.bitbucket.BitbucketPullRequestList
import com.msoa.extractor.domain.port.bitbucket.RequestCallback
import com.msoa.extractor.domain.port.persistence.PersistenceLogRequest
import com.msoa.extractor.infra.bitbucket.AbstractBitbucket
import com.msoa.extractor.infra.bitbucket.client.BitbucketClient
import org.springframework.stereotype.Service

@Service
class BitbucketPullRequestImpl(
    client: BitbucketClient<PullRequest>,
    requestCallback: RequestCallback,
    persistenceLogRequest: PersistenceLogRequest
) : AbstractBitbucket<PullRequest>(client, requestCallback, persistenceLogRequest), BitbucketPullRequestList {

    override fun list(repository: Repository, lastPullRequest: PullRequest?): List<PullRequest> {
        var filter = "(state=\"MERGED\" OR state=\"DECLINED\")"
        if (lastPullRequest != null)
            filter += " AND id>${lastPullRequest.id}"

        return mutableListOf<PullRequest>().apply {
            endpoint(
                baseUrl = repository.links?.pullrequests?.href!!,
                filter = filter,
                sortExp = "id"
            ).let {
                addAll(
                    list(it, PullRequest::class.java)
                )
            }
        }
    }
}
