package com.msoa.extractor.infra.bitbucket

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.msoa.extractor.domain.port.bitbucket.RequestCallback
import com.msoa.extractor.domain.port.persistence.PersistenceLogRequest
import com.msoa.extractor.infra.bitbucket.client.BitbucketClient
import com.msoa.extractor.infra.bitbucket.client.deserializer.LocalDateTimeDeserializer
import com.msoa.extractor.infra.bitbucket.client.deserializer.TypeDeserializer
import java.net.URLEncoder
import java.time.LocalDateTime

abstract class AbstractBitbucket<T>(
    private val client: BitbucketClient<T>,
    private val requestCallback: RequestCallback,
    private val persistenceLogRequest: PersistenceLogRequest
) {

    fun list(endpoint: String, classOfResult: Class<T>) =
        convert(
            client.list(
                endpoint = endpoint,
                requestCallback = requestCallback
            ),
            classOfResult
        )

    fun endpoint(
        baseUrl: String,
        filter: String? = null,
        sortExp: String? = null,
        encodeFilter: Boolean = false
    ): String {
        var url = baseUrl
        if (filter != null || sortExp != null) {
            url += "?"
            if (filter != null) {
                url += filter.let {
                    "q=${if (encodeFilter) URLEncoder.encode(filter) else filter}"
                }
            }
            url += if (filter != null && sortExp != null) "&" else ""
            url += sortExp?.let {
                "sort=$sortExp"
            } ?: ""
        }
        return url
    }

    fun notCalled(url: String) = persistenceLogRequest.requestSuccess(url).not()

    fun convert(list: List<T>, classOfResult: Class<T>): List<T> =
        mutableListOf<T>().apply {
            list.forEach {
                this.add(convert(it, classOfResult))
            }
        }

    fun convert(entity: T?, classOfResult: Class<T>): T {
        ObjectMapper().apply {
            this.registerModule(
                SimpleModule().apply {
                    addDeserializer(com.msoa.extractor.domain.model.Type::class.java, TypeDeserializer())
                    addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer())
                }
            )
        }.also {
            return it.convertValue(entity, classOfResult)
        }
    }
}
