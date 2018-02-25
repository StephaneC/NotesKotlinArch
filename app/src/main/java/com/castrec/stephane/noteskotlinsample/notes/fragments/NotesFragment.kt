package com.castrec.stephane.noteskotlinsample.notes.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.castrec.stephane.noteskotlinsample.R
import com.castrec.stephane.noteskotlinsample.commons.BaseTabFragment
import com.castrec.stephane.noteskotlinsample.di.NotesDH
import com.castrec.stephane.noteskotlinsample.notes.model.Note
import com.castrec.stephane.noteskotlinsample.notes.viewmodel.NotesViewModel
import com.castrec.stephane.noteskotlinsample.notes.viewmodel.NotesViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.List
import javax.inject.Inject

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
class NotesFragment : BaseTabFragment() {

    private val component by lazy { NotesDH.notesComponent() }

    @Inject
    lateinit var viewModelFactory: NotesViewModelFactory
    private val viewModel: NotesViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(NotesViewModel::class.java) }

    private val disposable = CompositeDisposable()

    private lateinit var adapter : NotesRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = super.onCreateView(inflater, container, savedInstanceState)

        adapter = NotesRecyclerViewAdapter(ArrayList<Note>());
        mV.adapter = adapter

        component.inject(this)


        return v
    }


    override fun onStart() {
        super.onStart()
        disposable.add(viewModel.fetchNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ notes: List<Note> -> updateNotes(notes) },
                        { error -> manageError(error) }))
    }

    private fun updateNotes(notes: List<Note>) {
        manageListState(notes.size)
        adapter.updateList(notes)
    }

    override fun onDetach() {
        super.onDetach()
        disposable.clear()
    }

    override fun getTitle(): Int {
        return R.string.title_notes
    }

    override fun getIcon(): Int {
        return android.R.drawable.ic_menu_week
    }

    companion object {

        fun newInstance(): NotesFragment {
            return NotesFragment()
        }
    }
}
