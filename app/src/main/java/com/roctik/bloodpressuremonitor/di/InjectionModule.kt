package com.roctik.bloodpressuremonitor.di

import com.roctik.bloodpressuremonitor.data.repository.InfoRepositoryImpl
import com.roctik.bloodpressuremonitor.data.repository.TrackRepositoryImpl
import com.roctik.bloodpressuremonitor.domain.repository.InfoRepository
import com.roctik.bloodpressuremonitor.domain.repository.TrackRepository
import com.roctik.bloodpressuremonitor.domain.usecase.InfoUseCase
import com.roctik.bloodpressuremonitor.domain.usecase.TrackUseCase
import com.roctik.bloodpressuremonitor.ui.info.InfoViewModel
import com.roctik.bloodpressuremonitor.ui.track.TrackViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val singleModule = module {
    single { PreferencesImpl(androidApplication()).provideSecurePreferences() } bind Preferences::class
    single { DatabaseModuleImpl(androidApplication()).provideAppDatabase() } bind DatabaseModule::class
    single { RetrofitModuleImpl().provideRetrofit() } bind RetrofitModule::class
}

val repositoryModule = module {
    single { TrackRepositoryImpl(get()) } bind TrackRepository::class
    single { InfoRepositoryImpl(get()) } bind InfoRepository::class
}

val useCaseModule = module {
    single { InfoUseCase(get()) }
    single { TrackUseCase(get()) }
}

val viewModelModule = module {
    viewModel { InfoViewModel(get()) }
    viewModel { TrackViewModel(get()) }
}