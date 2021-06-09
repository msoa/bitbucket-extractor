package com.msoa.extractor.infra.persistence.entity

import java.security.MessageDigest
import java.util.Base64

class HashHelper {
    companion object {
        fun md5Hash(str: String): String =
            String(Base64.getEncoder().encode(MessageDigest.getInstance("MD5").digest(str.toByteArray())))
    }
}
