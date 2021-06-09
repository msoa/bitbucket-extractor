package com.msoa.extractor.domain.port.bitbucket

import com.msoa.extractor.domain.model.Repository
import java.time.LocalDateTime

interface BitbucketRepositoryList {
    fun list(projectName: String?, createOn: LocalDateTime?): List<Repository>
}
