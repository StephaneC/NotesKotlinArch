package com.castrec.stephane.noteskotlinsample.chat.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.castrec.stephane.noteskotlinsample.R
import java.util.List
import com.castrec.stephane.noteskotlinsample.chat.model.Chat
import com.castrec.stephane.noteskotlinsample.commons.adapter.CustomBaseAdapter
import com.castrec.stephane.noteskotlinsample.commons.helper.DateHelper

/**
 * [RecyclerView.Adapter] that can display a [Chat] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ChatsRecyclerViewAdapter(private val mValues: ArrayList<Chat>) : CustomBaseAdapter<Chat, ChatsRecyclerViewAdapter.ViewHolder>(mValues) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mDateView.text = DateHelper.getFormattedDate(mValues[position].timestamp)
        holder.mContentView.text = mValues[position].message
        holder.mUsernameView.text = mValues[position].name

    }

    override fun getItemCount(): Int {
        return mValues.size
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mDateView: TextView
        val mUsernameView: TextView
        val mContentView: TextView
        var mItem: Chat? = null

        init {
            mDateView = mView.findViewById<View>(R.id.chat_date) as TextView
            mUsernameView = mView.findViewById<View>(R.id.chat_author) as TextView
            mContentView = mView.findViewById<View>(R.id.chat_message) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
