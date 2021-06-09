package com.msoa.extractor.infra.bitbucket.client

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.msoa.extractor.domain.model.BitbucketResponse
import com.msoa.extractor.domain.port.bitbucket.RequestCallback
import com.msoa.extractor.infra.config.logger
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import java.lang.reflect.Type
import kotlin.jvm.Throws

@Component
class BitbucketClient<T>(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson,
    @Value("\${bitbucket.authenticate}") private val authenticate: String
) {
    private val typeOfListResponse: Type = object : TypeToken<BitbucketResponse<T>>() {}.type
    private val typeOfGetResponse: Type = object : TypeToken<T>() {}.type

    @Throws(Exception::class)
    fun list(
        endpoint: String,
        encodeFilter: Boolean = false,
        requestCallback: RequestCallback
    ): List<T> = loadList(endpoint, requestCallback)

    @Throws(Exception::class)
    fun get(endpoint: String, requestCallback: RequestCallback): T? =
        loadOne(endpoint, requestCallback)

    private fun loadOne(endpoint: String, requestCallback: RequestCallback): T? {
        val response = request(endpoint, requestCallback)
        try {
            response?.body?.let {
                return gson.fromJson<T>(it.string(), typeOfGetResponse)
            }
        } finally {
            response?.body?.close()
            response?.close()
        }
        return null
    }

    private fun loadList(endpoint: String, requestCallback: RequestCallback): List<T> {
        val list = mutableListOf<T>()

        val response = request(endpoint, requestCallback)
        try {
            response?.body?.apply {
                gson.fromJson<BitbucketResponse<T>>(this.string(), typeOfListResponse).apply {
                    values?.let { list.addAll(it) }
                    next?.let { list.addAll(loadList(it, requestCallback)) }
                }
            }
        } finally {
            response?.body?.close()
            response?.close()
        }

        return list
    }

    private fun request(endpoint: String, requestCallback: RequestCallback): Response? {
        logger().info("URL $endpoint")

        val request = Request.Builder()
            .url(endpoint)
            .addHeader("Authorization", "Basic $authenticate")
            .get()
            .build()

        try {
            okHttpClient.newCall(request).execute().run {
                if (!isSuccessful) {
                    logger().error("Request error: {}", code)
                    throw HttpClientErrorException(HttpStatus.resolve(code) ?: HttpStatus.INTERNAL_SERVER_ERROR)
                }
                requestCallback.callback(endpoint, null, true)
                return this
            }
        } catch (e: Exception) {
            logger().error(e.message)
            if (e is HttpClientErrorException) {
                requestCallback.callback(endpoint, e.message, e.statusCode == HttpStatus.NOT_FOUND)
            } else {
                requestCallback.callback(endpoint, e.message, false)
            }
            return null
        }
    }
}
