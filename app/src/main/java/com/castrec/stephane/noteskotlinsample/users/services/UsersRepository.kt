package com.castrec.stephane.noteskotlinsample.users.services

import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import com.castrec.stephane.noteskotlinsample.users.model.Token
import com.castrec.stephane.noteskotlinsample.users.model.User
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.BackpressureStrategy



/**
 * Created by sca on 19/02/2018.
 *
 * In charge of managing data.
 * - Fetch from api and stor into DB
 */
class UsersRepository(
        private val local: UsersDataContract.Local,
        private val remote: UsersDataContract.Remote,
        private val scheduler: Scheduler,
        private val compositeDisposable: CompositeDisposable
): UsersDataContract.Repository {

    override fun saveUsers(users: List<User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signin(login: String, pwd: String): Flowable<Token> {
        return Flowable.create({ emitter: FlowableEmitter<Token> ->
            remote.signin(login, pwd).subscribe(
                    {token ->
                        local.saveToken(token)
                        emitter.onNext(token)
                        emitter.onComplete()
                    },
                    {error -> emitter.onError(error)})
        }, BackpressureStrategy.BUFFER)
    }

    override fun signup(login: String, pwd: String, photoUrl: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getToken(): Token {
        return local.getToken();
    }

    override fun fetchUsers(): Flowable<List<User>> {
        return Flowable.create({ emitter: FlowableEmitter<List<User>> ->
            remote.fetchUsers(getToken()).subscribe(
                    {users ->
                        local.saveUsers(users)
                        emitter.onNext(users)
                        emitter.onComplete()
                    },
                    {error -> emitter.onError(error)})
        }, BackpressureStrategy.BUFFER)
    }


    override fun handleError(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}