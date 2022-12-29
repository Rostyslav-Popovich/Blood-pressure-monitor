package com.roctik.bloodpressuremonitor.data.source.network.api

import com.roctik.bloodpressuremonitor.BuildConfig
import com.roctik.bloodpressuremonitor.data.source.network.model.ArticlesModel
import retrofit2.http.*

interface ApiService {

    @Headers("Accept: application/json")
    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("category") category: String,
        @Query("language") language: String
    ): ArticlesModel
}