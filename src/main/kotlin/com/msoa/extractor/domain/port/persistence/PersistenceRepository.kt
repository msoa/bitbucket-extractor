package com.msoa.extractor.domain.port.persistence

import com.msoa.extractor.domain.model.Repository
import java.time.LocalDateTime

interface PersistenceRepository {
    fun getLastDate(): LocalDateTime?
    fun find(projectName: String?): List<Repository>
    fun save(list: List<Repository>)
}
