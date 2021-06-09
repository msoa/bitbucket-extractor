package com.msoa.extractor.domain.port.persistence

import com.msoa.extractor.domain.model.PullRequest

interface Persistence<T> {
    fun find(pullRequest: PullRequest, classOfResult: Class<T>): List<T>
    fun save(pullRequest: PullRequest, list: List<T>, classOfResult: Class<T>)
}
