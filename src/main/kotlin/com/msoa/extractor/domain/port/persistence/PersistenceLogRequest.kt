package com.msoa.extractor.domain.port.persistence

import com.msoa.extractor.domain.port.bitbucket.RequestCallback

interface PersistenceLogRequest : RequestCallback {
    fun requestSuccess(url: String): Boolean
    fun save(url: String, message: String?, success: Boolean)
}
