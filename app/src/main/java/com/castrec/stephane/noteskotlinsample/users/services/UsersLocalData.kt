package com.castrec.stephane.noteskotlinsample.users.services

import android.content.SharedPreferences
import com.castrec.stephane.noteskotlinsample.users.model.Token
import com.castrec.stephane.noteskotlinsample.users.model.User
import io.reactivex.Flowable

/**
 * Created by sca on 18/02/2018.
 * Class to get remote data.
 */
class UsersLocalData(private val sharedPreferences: SharedPreferences): UsersDataContract.Local {

    val TOKEN = "token";

    override fun fetchUsers(): Flowable<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveUsers(users: List<User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getToken(): Token {
        val token = sharedPreferences.getString(TOKEN, null);
        return Token(token);
    }

    /**
     * Store token in sharedPref
     */
    override fun saveToken(token: Token) {
        val editor = sharedPreferences!!.edit()
        editor.putString(TOKEN, token.token)
        editor.apply()
    }


}