package com.roctik.bloodpressuremonitor.data.source.network.model

data class ArticlesModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)