package com.msoa.extractor.infra.persistence.repository

import com.msoa.extractor.infra.persistence.entity.CommitEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CommitRepository : JpaRepository<CommitEntity, String> {
    @Query("SELECT * FROM commit WHERE repository_uuid = ?1 AND pull_request_id = ?2", nativeQuery = true)
    fun findByRepositoryUuidAndPullRequestId(repositoryUuid: String, pullRequestId: Int): List<CommitEntity>
}
