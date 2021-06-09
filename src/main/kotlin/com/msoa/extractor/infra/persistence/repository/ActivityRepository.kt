package com.msoa.extractor.infra.persistence.repository

import com.msoa.extractor.infra.persistence.entity.ActivityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ActivityRepository : JpaRepository<ActivityEntity, String> {
    fun findByRepositoryUuidAndPullRequestId(repositoryUuid: String, pullRequestId: Int): List<ActivityEntity>
}
