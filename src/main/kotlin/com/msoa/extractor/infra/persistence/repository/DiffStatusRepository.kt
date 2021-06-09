package com.msoa.extractor.infra.persistence.repository

import com.msoa.extractor.infra.persistence.entity.DiffStatusEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiffStatusRepository : JpaRepository<DiffStatusEntity, String> {
    fun findByRepositoryUuidAndPullRequestId(repositoryUuid: String, pullRequestId: Int): List<DiffStatusEntity>
}
