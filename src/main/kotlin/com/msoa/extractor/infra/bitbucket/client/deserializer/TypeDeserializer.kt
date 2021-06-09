package com.msoa.extractor.infra.bitbucket.client.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.msoa.extractor.domain.model.Type
import java.io.IOException
import kotlin.jvm.Throws

class TypeDeserializer : StdDeserializer<Type>(Type::class.java) {
    @Throws(IOException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): Type {
        return Type.create(jp.text)
    }
}
