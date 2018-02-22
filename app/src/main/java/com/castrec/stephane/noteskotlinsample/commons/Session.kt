package com.castrec.stephane.noteskotlinsample.commons

import android.content.SharedPreferences
import com.castrec.stephane.noteskotlinsample.commons.model.Token
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by sca on 20/02/2018.
 */
interface Session {
    fun getToken():Token
    fun saveToken(token:Token)
}

class AppSession(val sharedPreferences: SharedPreferences): Session {

    override fun getToken(): Token {
        val token = sharedPreferences.getString("token", null);
        return Token(token);
    }

    override fun saveToken(token: Token) {
        val editor = sharedPreferences!!.edit()
        editor.putString("token", token.token)
        editor.apply()
    }
}