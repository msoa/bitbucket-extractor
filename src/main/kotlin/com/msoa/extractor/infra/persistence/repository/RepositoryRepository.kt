package com.msoa.extractor.infra.persistence.repository

import com.msoa.extractor.infra.persistence.entity.RepositoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

@org.springframework.stereotype.Repository
interface RepositoryRepository : JpaRepository<RepositoryEntity, String> {
    fun findByProjectNameContains(name: String): List<RepositoryEntity>

    @Query("SELECT MAX(create_on) FROM repository", nativeQuery = true)
    fun getMaxCreateOn(): LocalDateTime?
}
