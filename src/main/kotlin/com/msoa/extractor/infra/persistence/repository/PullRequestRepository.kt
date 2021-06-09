package com.msoa.extractor.infra.persistence.repository

import com.msoa.extractor.infra.persistence.entity.PullRequestEntity
import com.msoa.extractor.infra.persistence.entity.PullRequestPK
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

@org.springframework.stereotype.Repository
interface PullRequestRepository : JpaRepository<PullRequestEntity, PullRequestPK> {
    @Query("SELECT * FROM pull_request WHERE repository_uuid = ?1 ORDER BY id DESC LIMIT 1", nativeQuery = true)
    fun findFirstByUuidOrderByIdDesc(uuid: String): PullRequestEntity?

    @Query("SELECT * FROM pull_request WHERE repository_uuid = ?1 ORDER BY id", nativeQuery = true)
    fun findAllByUuid(uuid: String): List<PullRequestEntity>

    @Query("SELECT MAX(updated_on) FROM pull_request WHERE repository_uuid = ?1", nativeQuery = true)
    fun getMaxUpdatedOnByUuid(uuid: String): LocalDateTime
}
