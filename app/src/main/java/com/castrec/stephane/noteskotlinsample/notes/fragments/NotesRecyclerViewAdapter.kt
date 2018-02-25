package com.castrec.stephane.noteskotlinsample.notes.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.castrec.stephane.noteskotlinsample.R
import com.castrec.stephane.noteskotlinsample.commons.adapter.CustomBaseAdapter
import com.castrec.stephane.noteskotlinsample.commons.helper.DateHelper
import com.castrec.stephane.noteskotlinsample.notes.model.Note
import java.util.List



/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to theh
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class NotesRecyclerViewAdapter(private val mValues: ArrayList<Note>) :
        CustomBaseAdapter<Note, NotesRecyclerViewAdapter.ViewHolder>(mValues) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mUserView.text = mValues[position].name
        holder.mdateView.text = DateHelper.getFormattedDate(mValues[position].timestamp)
        //holder.mActionView.checked = mValues[position].name
        holder.mContentView.text = mValues[position].message
    }

    override fun getItemCount(): Int {
        return mValues.size
    }



    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mdateView: TextView
        val mUserView: TextView
        val mActionView: CheckBox
        val mContentView: TextView
        var mItem: Note? = null

        init {
            mdateView = mView.findViewById<View>(R.id.note_date) as TextView
            mUserView = mView.findViewById<View>(R.id.note_user) as TextView
            mActionView = mView.findViewById<View>(R.id.note_check) as CheckBox
            mContentView = mView.findViewById<View>(R.id.note_message) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
