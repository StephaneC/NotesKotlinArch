package com.castrec.stephane.noteskotlinsample.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by sca on 19/02/2018.
 */
@Module
class StorageModule {
    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}