package com.msoa.extractor.infra.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

const val HTTP_SECONDS_TIMEOUT: Long = 30

@Configuration
class RestConfig {
    @Bean
    @Qualifier(value = "OkHttpClient")
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(HTTP_SECONDS_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(HTTP_SECONDS_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HTTP_SECONDS_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Bean
    fun restTemplate(): RestTemplate? {
        return RestTemplate()
    }

    @Bean
    fun gson() = GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, GsonDateSerializer())
        .registerTypeAdapterFactory(GsonKotlinAdapterFactory(false))
        .create()

    internal class GsonDateSerializer : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
        @Synchronized
        override fun serialize(date: LocalDateTime, type: Type, jsonSerializationContext: JsonSerializationContext): JsonElement {
            return JsonPrimitive(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(date))
        }

        @Synchronized
        override fun deserialize(
            jsonElement: JsonElement,
            type: Type,
            jsonDeserializationContext: JsonDeserializationContext
        ): LocalDateTime {
            return LocalDateTime.parse(jsonElement.asString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        }
    }

    internal class GsonKotlinAdapterFactory(private val checkNulls: Boolean = true) : TypeAdapterFactory {
        private val Class<*>.isKotlinClass: Boolean
            get() = declaredAnnotations.find { it.annotationClass.java.name == "kotlin.Metadata" } != null

        override fun <T : Any> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
            if (!type.rawType.isKotlinClass) {
                return null
            }

            val kClass = (type.rawType as Class<*>).kotlin

            @Suppress("UNCHECKED_CAST")
            return Adapter(this, gson, type, kClass as KClass<T>, checkNulls)
        }

        class Adapter<T : Any>(
            factory: GsonKotlinAdapterFactory,
            gson: Gson,
            type: TypeToken<T>,
            private val kClass: KClass<T>,
            private val checkNulls: Boolean
        ) : TypeAdapter<T>() {
            private val delegate: TypeAdapter<T> = gson.getDelegateAdapter(factory, type)

            override fun write(out: JsonWriter, value: T?) {
                delegate.write(out, value)
            }

            override fun read(input: JsonReader): T {
                return delegate.read(input).apply {
                    if (checkNulls) doNullCheck(this)
                }
            }

            private fun doNullCheck(value: T) {
                kClass.declaredMemberProperties.forEach { prop ->
                    prop.isAccessible = true
                    if (!prop.returnType.isMarkedNullable && prop(value) == null) {
                        throw JsonParseException("Campo: '${prop.name}' do '${value.javaClass.simpleName}' deve ser informado")
                    }
                }
            }
        }
    }
}
