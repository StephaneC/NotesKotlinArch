package com.castrec.stephane.noteskotlinsample.di

import android.content.SharedPreferences
import com.castrec.stephane.noteskotlinsample.commons.AppSession
import com.castrec.stephane.noteskotlinsample.commons.Session
import com.castrec.stephane.noteskotlinsample.commons.model.Token
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by sca on 18/02/2018.
 */

@Module
@Singleton
class SessionModule {

    @Provides
    @Singleton
    fun providesToken(sharedPreferences: SharedPreferences): Token {
        val token = sharedPreferences.getString("token", null);
        return Token(token);
    }

    @Provides
    @Singleton
    fun providesSession(sharedPreferences: SharedPreferences): Session {
        return AppSession(sharedPreferences);
    }

}