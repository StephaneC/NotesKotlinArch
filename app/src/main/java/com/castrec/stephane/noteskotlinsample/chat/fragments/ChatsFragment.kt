package com.castrec.stephane.noteskotlinsample.users.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.castrec.stephane.noteskotlinsample.R
import com.castrec.stephane.noteskotlinsample.chat.fragments.ChatsRecyclerViewAdapter
import com.castrec.stephane.noteskotlinsample.chat.model.Chat
import com.castrec.stephane.noteskotlinsample.chat.viewmodel.ChatsViewModel
import com.castrec.stephane.noteskotlinsample.chat.viewmodel.ChatsViewModelFactory
import com.castrec.stephane.noteskotlinsample.commons.BaseTabFragment
import com.castrec.stephane.noteskotlinsample.di.NotesDH
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.List
import javax.inject.Inject
import java.util.ArrayList

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
class ChatsFragment : BaseTabFragment() {

    private val component by lazy { NotesDH.chatsComponent() }

    @Inject
    lateinit var viewModelFactory: ChatsViewModelFactory
    private val viewModel: ChatsViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(ChatsViewModel::class.java) }

    private val disposable = CompositeDisposable()

    private lateinit var adapter : ChatsRecyclerViewAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = super.onCreateView(inflater, container, savedInstanceState)

        adapter = ChatsRecyclerViewAdapter(ArrayList<Chat>());
        mV.adapter = adapter

        component.inject(this)

        return v
    }


    override fun onStart() {
        super.onStart()
        disposable.add(viewModel.fetchChats()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ users: List<Chat> -> updateChats(users) },
                        { error -> manageError(error) }))
    }

    private fun updateChats(users: List<Chat>) {
        manageListState(users.size)

        adapter.updateList(users)

    }

    override fun onDetach() {
        super.onDetach()
        disposable.clear()
    }

    override fun getTitle(): Int {
        return R.string.title_chats
    }

    override fun getIcon(): Int {
        return android.R.drawable.ic_menu_edit
    }

    companion object {

        fun newInstance(): ChatsFragment {
            return ChatsFragment()
        }
    }
}
