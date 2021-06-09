package com.msoa.extractor.domain.usecase

import com.msoa.extractor.domain.model.Commit
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.port.bitbucket.BitbucketList
import com.msoa.extractor.domain.port.persistence.Persistence
import org.springframework.stereotype.Component

@Component
class ListCommits(
    private val bitbucket: BitbucketList<Commit>,
    private val persistence: Persistence<Commit>,
) {
    fun execute(pullRequest: PullRequest) =
        mutableListOf<Commit>().apply {
            addAll(persistence.find(pullRequest, Commit::class.java))
            addAll(
                bitbucket.list(pullRequest, Commit::class.java).apply {
                    persistence.save(pullRequest, this, Commit::class.java)
                }
            )
        }
}
