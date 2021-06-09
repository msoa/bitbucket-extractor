package com.msoa.extractor.infra.persistence.repository

import com.msoa.extractor.infra.persistence.entity.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<CommentEntity, String> {
    fun findByRepositoryUuidAndPullRequestId(repositoryUuid: String, pullRequestId: Int): List<CommentEntity>
}
