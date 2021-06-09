package com.msoa.extractor.infra.persistence.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "log_request")
class LogRequestEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
    @Column(name = "url") var url: String?,
    @Column(name = "success") var success: Boolean?,
    @Column(name = "error_message") var errorMessage: String?,
    @Column(name = "create_on") var createOn: LocalDateTime?
) {
    companion object {
        fun build(endpoint: String, message: String?, success: Boolean) =
            LogRequestEntity(
                id = null,
                createOn = LocalDateTime.now(),
                success = success,
                errorMessage = message,
                url = endpoint
            )
    }
}
