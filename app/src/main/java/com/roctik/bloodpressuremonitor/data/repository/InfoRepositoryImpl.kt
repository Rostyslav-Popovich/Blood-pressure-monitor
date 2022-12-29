package com.roctik.bloodpressuremonitor.data.repository

import com.roctik.bloodpressuremonitor.data.source.network.api.ApiService
import com.roctik.bloodpressuremonitor.data.source.network.model.ArticlesModel
import com.roctik.bloodpressuremonitor.domain.repository.InfoRepository

class InfoRepositoryImpl(private val retrofitService: ApiService): InfoRepository {
    override suspend fun getArticles(category: String, language: String): ArticlesModel {
        return retrofitService.getArticles(category = category, language = language)
    }

}