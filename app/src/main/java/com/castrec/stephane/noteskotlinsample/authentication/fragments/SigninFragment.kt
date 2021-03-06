package com.castrec.stephane.noteskotlinsample.authentication.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.castrec.stephane.noteskotlinsample.MainActivity
import com.castrec.stephane.noteskotlinsample.R
import com.castrec.stephane.noteskotlinsample.di.NotesDH
import com.castrec.stephane.noteskotlinsample.commons.model.Token
import com.castrec.stephane.noteskotlinsample.users.viewmodel.AuthenticationViewModel
import com.castrec.stephane.noteskotlinsample.users.viewmodel.AuthenticationViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by sca on 19/02/2018.
 */
class SigninFragment: Fragment() {
    private val component by lazy { NotesDH.authenticationComponent() }


    @Inject
    lateinit var viewModelFactory: AuthenticationViewModelFactory

    private val viewModel: AuthenticationViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(AuthenticationViewModel::class.java) }

    private val disposable = CompositeDisposable()

    companion object {

        fun newInstance(): SigninFragment {
            return SigninFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v: View? = inflater?.inflate(R.layout.fragment_signin, container, false)
        val login: EditText? = v?.findViewById(R.id.signin_login)
        val pwd: EditText? = v?.findViewById(R.id.signin_pwd)

        component.inject(this)


        v?.findViewById<Button>(R.id.signin_connect)?.setOnClickListener(View.OnClickListener {
            //user clicked button
            if(login?.text!!.isEmpty()){
                login?.setError("Should'nt be empty")
            }
            if(login?.text!!.isEmpty()){
                login?.setError("Should'nt be empty")
            }
            //TODO manage loader
            disposable.add(viewModel.signin(login?.text.toString(), pwd?.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ token: Token -> loggedIn(token) },
                            { error -> loginError(error) }))
        });

        return v;
    }

    fun loginError(error: Throwable) {
        Toast.makeText(context, context?.getString(R.string.login_error), Toast.LENGTH_LONG).show();
        Log.w("SigninFragment", "Error login", error)
    }

    fun loggedIn(token: Token){
        val i : Intent = Intent(this.context, MainActivity::class.java)
        startActivity(i)
    }
}