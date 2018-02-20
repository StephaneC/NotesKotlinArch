package com.castrec.stephane.noteskotlinsample.users.services

import com.castrec.stephane.noteskotlinsample.users.model.Token
import com.castrec.stephane.noteskotlinsample.users.model.User
import io.reactivex.Flowable

/**
 * Created by sca on 18/02/2018.
 * Class to get remote data.
 */
class UsersRemoteData(private val usersService: UsersServices): UsersDataContract.Remote {
    override fun fetchUsers(token: Token): Flowable<List<User>> {
        return usersService.fetchUsers(token.token);
    }

    override fun signin(login: String, pwd: String): Flowable<Token> {
        return usersService.signin(login, pwd);
    }

    override fun signup(login: String, pwd: String, photoUrl: String) {
        return usersService.signup(login, pwd, photoUrl);
    }

}