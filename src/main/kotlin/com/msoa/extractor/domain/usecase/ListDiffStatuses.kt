package com.msoa.extractor.domain.usecase

import com.msoa.extractor.domain.model.DiffStatus
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.port.bitbucket.BitbucketList
import com.msoa.extractor.domain.port.persistence.Persistence
import org.springframework.stereotype.Component

@Component
class ListDiffStatuses(
    private val bitbucket: BitbucketList<DiffStatus>,
    private val persistence: Persistence<DiffStatus>,
) {
    fun execute(pullRequest: PullRequest) =
        mutableListOf<DiffStatus>().apply {
            addAll(persistence.find(pullRequest, DiffStatus::class.java))
            addAll(
                bitbucket.list(pullRequest, DiffStatus::class.java).apply {
                    persistence.save(pullRequest, this, DiffStatus::class.java)
                }
            )
        }
}
