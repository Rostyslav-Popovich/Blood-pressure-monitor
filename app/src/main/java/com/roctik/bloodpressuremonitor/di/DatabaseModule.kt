package com.roctik.bloodpressuremonitor.di

import com.roctik.bloodpressuremonitor.data.source.local.AppDatabase

interface DatabaseModule {
    fun provideAppDatabase(): AppDatabase
}