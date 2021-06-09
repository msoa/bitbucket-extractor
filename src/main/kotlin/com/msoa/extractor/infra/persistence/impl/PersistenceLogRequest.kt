package com.msoa.extractor.infra.persistence.impl

import com.msoa.extractor.domain.port.bitbucket.RequestCallback
import com.msoa.extractor.domain.port.persistence.PersistenceLogRequest
import com.msoa.extractor.infra.persistence.entity.LogRequestEntity
import com.msoa.extractor.infra.persistence.repository.LogRequestRepository
import org.springframework.stereotype.Component

@Component
class PersistenceLogRequest(
    private val logRequestRepository: LogRequestRepository,
) : PersistenceLogRequest, RequestCallback {
    override fun requestSuccess(url: String): Boolean =
        logRequestRepository.existsByUrlAndSuccess(url, true)

    override fun save(url: String, message: String?, success: Boolean) {
        logRequestRepository.save(
            LogRequestEntity.build(url, message, success)
        )
    }

    override fun callback(url: String, message: String?, success: Boolean) =
        this.save(url, message, success)
}
