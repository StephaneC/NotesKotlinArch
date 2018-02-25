package com.castrec.stephane.noteskotlinsample.users.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.castrec.stephane.noteskotlinsample.R
import com.castrec.stephane.noteskotlinsample.commons.adapter.CustomBaseAdapter
import com.castrec.stephane.noteskotlinsample.users.model.User

/**
 * [RecyclerView.Adapter] that can display a user
 */
class UserRecyclerViewAdapter(private val mValues: ArrayList<User>) : CustomBaseAdapter<User, UserRecyclerViewAdapter.ViewHolder>(mValues) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mIdView.text = mValues[position].name
        holder.mContentView.text = mValues[position].timestamp.toString()
    }

    override fun getItemCount(): Int {
        return mValues.size
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView
        val mContentView: TextView
        var mItem: User? = null

        init {
            mIdView = mView.findViewById<View>(R.id.id) as TextView
            mContentView = mView.findViewById<View>(R.id.content) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
