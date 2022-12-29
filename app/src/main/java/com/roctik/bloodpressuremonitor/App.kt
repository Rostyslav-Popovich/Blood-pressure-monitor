package com.roctik.bloodpressuremonitor

import androidx.multidex.MultiDexApplication
import com.roctik.bloodpressuremonitor.di.repositoryModule
import com.roctik.bloodpressuremonitor.di.singleModule
import com.roctik.bloodpressuremonitor.di.useCaseModule
import com.roctik.bloodpressuremonitor.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                singleModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}