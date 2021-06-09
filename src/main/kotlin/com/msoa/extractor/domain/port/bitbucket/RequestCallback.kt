package com.msoa.extractor.domain.port.bitbucket

interface RequestCallback {
    fun callback(url: String, message: String?, success: Boolean)
}
