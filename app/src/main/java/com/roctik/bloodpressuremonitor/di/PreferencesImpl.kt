package com.roctik.bloodpressuremonitor.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.roctik.bloodpressuremonitor.BuildConfig

class PreferencesImpl(private val application: Application) : Preferences {

    override fun provideSecurePreferences(): SharedPreferences =
        application.getSharedPreferences(BuildConfig.SECURE_PREFS_FILE_KEY, Context.MODE_PRIVATE)
}