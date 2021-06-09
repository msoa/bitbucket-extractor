package com.msoa.extractor.domain.usecase

import com.msoa.extractor.domain.model.Activity
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.port.bitbucket.BitbucketList
import com.msoa.extractor.domain.port.persistence.Persistence
import org.springframework.stereotype.Component

@Component
class ListActivities(
    private val bitbucket: BitbucketList<Activity>,
    private val persistence: Persistence<Activity>,
) {
    fun execute(pullRequest: PullRequest) =
        mutableListOf<Activity>().apply {
            addAll(persistence.find(pullRequest, Activity::class.java))
            addAll(
                bitbucket.list(pullRequest, Activity::class.java).apply {
                    persistence.save(pullRequest, this, Activity::class.java)
                }
            )
        }
}
