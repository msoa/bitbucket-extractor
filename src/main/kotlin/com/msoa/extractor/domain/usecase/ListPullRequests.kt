package com.msoa.extractor.domain.usecase

import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.model.Repository
import com.msoa.extractor.domain.port.bitbucket.BitbucketPullRequestList
import com.msoa.extractor.domain.port.persistence.PersistencePullRequest
import org.springframework.stereotype.Component

@Component
class ListPullRequests(
    private val bitbucket: BitbucketPullRequestList,
    private val persistence: PersistencePullRequest
) {
    fun execute(repository: Repository) =
        mutableListOf<PullRequest>().apply {
            addAll(persistence.find(repository))
            addAll(
                persistence.findLast(repository).let {
                    bitbucket.list(repository, it).apply {
                        persistence.save(this)
                    }
                }
            )
        }
}
