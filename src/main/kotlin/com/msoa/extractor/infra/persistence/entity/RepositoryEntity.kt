package com.msoa.extractor.infra.persistence.entity

import com.google.gson.Gson
import com.msoa.extractor.domain.model.Repository
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "repository")
class RepositoryEntity(
    @Id
    @Column(name = "uuid")
    var uuid: String?,
    @Column(name = "name")
    var name: String?,
    @Column(name = "project_name")
    var projectName: String?,
    @Column(name = "create_on")
    var createOn: LocalDateTime?,
    @Column(name = "updated_on")
    var updatedOn: LocalDateTime?,
    @Column(name = "json")
    var json: String?
) {
    companion object {
        fun build(repository: Repository, gson: Gson) = repository.let {
            RepositoryEntity(
                uuid = it.uuid,
                name = it.name,
                createOn = it.createdOn,
                updatedOn = it.updatedOn,
                projectName = it.project!!.name,
                json = gson.toJson(it)
            )
        }
    }
}
