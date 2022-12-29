package com.roctik.bloodpressuremonitor.domain.usecase

import com.roctik.bloodpressuremonitor.data.source.network.model.Article
import com.roctik.bloodpressuremonitor.domain.repository.InfoRepository

class InfoUseCase(private val infoRepository: InfoRepository) {

    suspend fun getArticles(category: String = "health", language: String = "en"): List<Article> {
        return infoRepository.getArticles(category, language).articles
    }
}