package com.msoa.extractor.infra.persistence.repository

import com.msoa.extractor.infra.persistence.entity.LogRequestEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LogRequestRepository : JpaRepository<LogRequestEntity, Long> {
    fun existsByUrlAndSuccess(url: String, success: Boolean): Boolean
    fun findFirstByUrlOrderByIdDesc(url: String): LogRequestEntity?
}
