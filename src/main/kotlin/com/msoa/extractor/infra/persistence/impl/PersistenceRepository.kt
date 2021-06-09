package com.msoa.extractor.infra.persistence.impl

import com.google.gson.Gson
import com.msoa.extractor.domain.model.Repository
import com.msoa.extractor.domain.port.persistence.PersistenceRepository
import com.msoa.extractor.infra.persistence.entity.RepositoryEntity
import com.msoa.extractor.infra.persistence.repository.RepositoryRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PersistenceRepository(
    private val repositoryRepository: RepositoryRepository,
    private val gson: Gson
) : PersistenceRepository {
    override fun getLastDate(): LocalDateTime? = repositoryRepository.getMaxCreateOn()

    override fun find(projectName: String?): List<Repository> =
        mutableListOf<Repository>().apply {
            (
                projectName?.let {
                    repositoryRepository.findByProjectNameContains(projectName)
                } ?: repositoryRepository.findAll()
                ).forEach {
                this.add(gson.fromJson(it.json, Repository::class.java))
            }
        }

    override fun save(list: List<Repository>) {
        list.forEach {
            RepositoryEntity.build(it, gson).let { ent ->
                repositoryRepository.save(ent)
            }
        }
    }
}
