package com.castrec.stephane.noteskotlinsample.users.services

import com.castrec.stephane.noteskotlinsample.users.model.Token
import com.castrec.stephane.noteskotlinsample.users.model.User
import io.reactivex.Flowable

/**
 * Created by sca on 18/02/2018.
 *
 * This class describe every interactions we can do with data
 */
interface UsersDataContract {
    /** call to db. */
    interface Repository {
        //Subject pattern - Will use Flowable for now
        //val usersFetchOutcome: PublishSubject<List<User>>
        //val tokenFetchOutcome: PublishSubject<Token>
        fun fetchUsers():Flowable<List<User>>
        fun saveUsers(users: List<User>)
        fun signin(login: String, pwd: String): Flowable<Token>
        fun signup(login: String, pwd: String, photoUrl: String)
        fun getToken(): Token
        fun handleError(error: Throwable)
    }

    /** call to **/
    interface Local {
        fun fetchUsers(): Flowable<List<User>>
        fun saveUsers(users: List<User>)
        fun getToken(): Token
        fun saveToken(token:Token)
    }

    /** call to api. */
    interface Remote {
        fun fetchUsers(token: Token): Flowable<List<User>>
        fun signin(login: String, pwd: String): Flowable<Token>
        fun signup(login: String, pwd: String, photoUrl: String)
    }
}