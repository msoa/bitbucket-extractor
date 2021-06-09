package com.msoa.extractor.infra.bitbucket.impl

import com.msoa.extractor.domain.model.Repository
import com.msoa.extractor.domain.port.bitbucket.BitbucketRepositoryList
import com.msoa.extractor.domain.port.bitbucket.RequestCallback
import com.msoa.extractor.domain.port.persistence.PersistenceLogRequest
import com.msoa.extractor.infra.bitbucket.AbstractBitbucket
import com.msoa.extractor.infra.bitbucket.client.BitbucketClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class BitbucketRepositoryImpl(
    client: BitbucketClient<Repository>,
    requestCallback: RequestCallback,
    persistenceLogRequest: PersistenceLogRequest,
    @Value("\${bitbucket.owner}") private val owner: String
) : AbstractBitbucket<Repository>(client, requestCallback, persistenceLogRequest), BitbucketRepositoryList {
    override fun list(projectName: String?, createOn: LocalDateTime?): List<Repository> {
        var filter = projectName?.let {
            "project.name~\"$projectName\""
        }
        if (createOn != null) {
            filter += if (filter != null) " AND " else ""
            filter += "created_on >= ${createOn.format(DateTimeFormatter.ISO_DATE)}"
        }

        endpoint(
            baseUrl = "https://api.bitbucket.org/2.0/repositories/$owner",
            filter = filter,
            sortExp = "created_on"
        ).let {
            return list(it, Repository::class.java)
        }
    }
}
