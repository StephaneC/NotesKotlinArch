package com.castrec.stephane.noteskotlinsample.chat.services

import com.castrec.stephane.noteskotlinsample.chat.model.Chat
import io.reactivex.Flowable
import java.util.List
/**
 * Created by sca on 18/02/2018.
 *
 * This class describe every interactions we can do with data
 */
interface ChatsDataContract {
    /** call to db. */
    interface Repository {
        //Subject pattern - Will use Flowable for now
        //val usersFetchOutcome: PublishSubject<List<User>>
        //val tokenFetchOutcome: PublishSubject<Token>
        fun fetchChats():Flowable<List<Chat>>
    }

    /** call to **/
    interface Local {
        fun fetchChats(): Flowable<List<Chat>>
        fun saveChats(users: List<Chat>)
    }

    /** call to api. */
    interface Remote {
        fun fetchChats(): Flowable<List<Chat>>
    }
}