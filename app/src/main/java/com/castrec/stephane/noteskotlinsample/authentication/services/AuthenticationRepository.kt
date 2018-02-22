package com.castrec.stephane.noteskotlinsample.users.services

import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import com.castrec.stephane.noteskotlinsample.commons.model.Token
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by sca on 19/02/2018.
 *
 * In charge of managing data.
 * - Fetch from api and stor into DB
 */
class AuthenticationRepository(
        private val session: com.castrec.stephane.noteskotlinsample.commons.Session,
        private val remote: AuthenticationDataContract.Remote,
        private val scheduler: Scheduler,
        private val compositeDisposable: CompositeDisposable
): AuthenticationDataContract.Repository {


    override fun signin(login: String, pwd: String): Flowable<Token> {
        return Flowable.create({ emitter: FlowableEmitter<Token> ->
            remote.signin(login, pwd).subscribe(
                    {token ->
                        session.saveToken(token)
                        emitter.onNext(token)
                        emitter.onComplete()
                    },
                    {error -> emitter.onError(error)})
        }, BackpressureStrategy.BUFFER)
    }

    override fun signup(login: String, pwd: String, photoUrl: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}