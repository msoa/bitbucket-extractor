package com.msoa.extractor.domain.port.persistence

import com.msoa.extractor.domain.model.PullRequest
import com.msoa.extractor.domain.model.Repository

interface PersistencePullRequest {
    fun findLast(repository: Repository): PullRequest?
    fun find(repository: Repository): List<PullRequest>
    fun save(list: List<PullRequest>)
}
