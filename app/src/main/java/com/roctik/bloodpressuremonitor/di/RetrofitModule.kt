package com.roctik.bloodpressuremonitor.di

import com.roctik.bloodpressuremonitor.data.source.network.api.ApiService

interface RetrofitModule {
    fun provideRetrofit(): ApiService
}