package com.msoa.extractor.domain.usecase

import com.msoa.extractor.domain.model.Comment
import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.port.bitbucket.BitbucketList
import com.msoa.extractor.domain.port.persistence.Persistence
import org.springframework.stereotype.Component

@Component
class ListComments(
    private val bitbucket: BitbucketList<Comment>,
    private val persistence: Persistence<Comment>,
) {
    fun execute(pullRequest: PullRequest) =
        mutableListOf<Comment>().apply {
            addAll(persistence.find(pullRequest, Comment::class.java))
            addAll(
                bitbucket.list(pullRequest, Comment::class.java).apply {
                    persistence.save(pullRequest, this, Comment::class.java)
                }
            )
        }
}
