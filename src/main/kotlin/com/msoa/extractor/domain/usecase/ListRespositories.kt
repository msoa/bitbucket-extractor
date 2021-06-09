package com.msoa.extractor.domain.usecase

import com.msoa.extractor.domain.model.Repository
import com.msoa.extractor.domain.port.bitbucket.BitbucketRepositoryList
import com.msoa.extractor.domain.port.persistence.PersistenceRepository
import org.springframework.stereotype.Component

@Component
class ListRespositories(
    private val bitbucket: BitbucketRepositoryList,
    private val persistence: PersistenceRepository
) {
    fun execute(projectName: String?) =
        mutableListOf<Repository>().apply {
            addAll(persistence.find(projectName))
            addAll(
                persistence.getLastDate().let {
                    bitbucket.list(projectName, it).apply {
                        persistence.save(this)
                    }
                }
            )
        }
}
