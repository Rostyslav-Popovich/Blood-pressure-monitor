package com.roctik.bloodpressuremonitor.di

import android.app.Application
import androidx.room.Room
import com.roctik.bloodpressuremonitor.data.source.local.AppDatabase
import com.roctik.bloodpressuremonitor.util.Constants

class DatabaseModuleImpl(private val application: Application) : DatabaseModule {

    override fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            Constants.DB_NAME
        )
            .build()
    }
}