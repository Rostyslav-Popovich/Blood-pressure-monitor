package com.roctik.bloodpressuremonitor.di

import android.content.SharedPreferences

interface Preferences {
    fun provideSecurePreferences(): SharedPreferences
}