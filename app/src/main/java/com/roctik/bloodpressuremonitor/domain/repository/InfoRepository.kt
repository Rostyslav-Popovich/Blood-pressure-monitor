package com.roctik.bloodpressuremonitor.domain.repository

import com.roctik.bloodpressuremonitor.data.source.network.model.ArticlesModel
import com.roctik.bloodpressuremonitor.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface InfoRepository {
    suspend fun getArticles(category: String, language: String): ArticlesModel
}