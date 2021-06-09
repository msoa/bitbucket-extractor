package com.msoa.extractor.infra.bitbucket.client.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.io.IOException
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.jvm.Throws

class LocalDateTimeDeserializer : StdDeserializer<LocalDateTime>(LocalDateTime::class.java) {
    @Throws(IOException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): LocalDateTime {
        val odt = OffsetDateTime.parse(jp.text)

        val zoneId = ZoneId.of("UTC")
        val zdt: ZonedDateTime = odt.atZoneSimilarLocal(zoneId)

        return zdt.toLocalDateTime()
    }
}
