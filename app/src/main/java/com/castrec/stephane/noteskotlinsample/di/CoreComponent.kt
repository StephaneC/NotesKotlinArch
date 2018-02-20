package com.castrec.stephane.noteskotlinsample.di

import android.content.Context
import android.content.SharedPreferences
import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import com.castrec.stephane.noteskotlinsample.users.di.AuthenticationModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by sca on 18/02/2018.
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, StorageModule::class, AuthenticationModule::class])
interface CoreComponent {

    fun context(): Context

    fun retrofit(): Retrofit

    //fun picasso(): Picasso

    fun sharedPreferences(): SharedPreferences

    fun scheduler(): Scheduler
}