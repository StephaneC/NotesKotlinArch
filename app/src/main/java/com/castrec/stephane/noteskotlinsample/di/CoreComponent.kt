package com.castrec.stephane.noteskotlinsample.di

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaCas
import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import com.castrec.stephane.noteskotlinsample.commons.Session
import com.castrec.stephane.noteskotlinsample.commons.model.Token
import com.castrec.stephane.noteskotlinsample.users.di.AuthenticationModule
import com.castrec.stephane.noteskotlinsample.users.di.UsersModule
import com.squareup.picasso.Picasso
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by sca on 18/02/2018.
 */
@Singleton
@Component(modules = [AppModule::class, SessionModule::class, NetworkModule::class, StorageModule::class, AuthenticationModule::class, UsersModule::class])
interface CoreComponent {

    fun context(): Context

    fun retrofit(): Retrofit

    //fun picasso(): Picasso

    fun sharedPreferences(): SharedPreferences

    fun scheduler(): Scheduler

    fun session(): Session
}