package com.castrec.stephane.noteskotlinsample.users.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.castrec.stephane.noteskotlinsample.R
import com.castrec.stephane.noteskotlinsample.commons.BaseTabFragment
import com.castrec.stephane.noteskotlinsample.di.NotesDH
import com.castrec.stephane.noteskotlinsample.users.model.User
import com.castrec.stephane.noteskotlinsample.users.viewmodel.UsersViewModel
import com.castrec.stephane.noteskotlinsample.users.viewmodel.UsersViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject
import java.util.List

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class UsersFragment : BaseTabFragment() {

    private val component by lazy { NotesDH.usersComponent() }

    @Inject
    lateinit var viewModelFactory: UsersViewModelFactory
    private val viewModel: UsersViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(UsersViewModel::class.java) }

    private val disposable = CompositeDisposable()

    private lateinit var adapter : UserRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = super.onCreateView(inflater, container, savedInstanceState)

        adapter = UserRecyclerViewAdapter(ArrayList<User>());
        mV.adapter = adapter

        component.inject(this)

        return v
    }



    override fun onStart() {
        super.onStart()
        disposable.add(viewModel.fetchUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ users: List<User> -> updateUsers(users) },
                        { error -> manageError(error) }))
    }

    private fun updateUsers(users: List<User>) {
        adapter.updateList(users)
    }

    override fun onDetach() {
        super.onDetach()
        disposable.clear()
    }

    override fun getTitle(): Int {
        return R.string.title_users
    }

    override fun getIcon(): Int {
        return android.R.drawable.ic_menu_info_details
    }

    companion object {

        fun newInstance(): UsersFragment {
            return UsersFragment()
        }
    }
}
