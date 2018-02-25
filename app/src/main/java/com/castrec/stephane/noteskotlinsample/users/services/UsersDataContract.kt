package com.castrec.stephane.noteskotlinsample.users.services

import com.castrec.stephane.noteskotlinsample.users.model.User
import io.reactivex.Flowable
import java.util.List

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
    }

    /** call to **/
    interface Local {
        fun fetchUsers(): Flowable<List<User>>
        fun saveUsers(users: List<User>)
    }

    /** call to api. */
    interface Remote {
        fun fetchUsers(): Flowable<List<User>>
    }
}