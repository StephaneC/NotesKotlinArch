package com.castrec.stephane.noteskotlinsample.users.services

import com.castrec.stephane.noteskotlinsample.commons.model.Token
import io.reactivex.Flowable

/**
 * Created by sca on 18/02/2018.
 *
 * This class describe every interactions we can do with data
 */
interface AuthenticationDataContract {
    /** call to db. */
    interface Repository {
        fun signin(login: String, pwd: String): Flowable<Token>
        fun signup(login: String, pwd: String, photoUrl: String)
    }

    /** call to api. */
    interface Remote {
        fun signin(login: String, pwd: String): Flowable<Token>
        fun signup(login: String, pwd: String, photoUrl: String)
    }
}