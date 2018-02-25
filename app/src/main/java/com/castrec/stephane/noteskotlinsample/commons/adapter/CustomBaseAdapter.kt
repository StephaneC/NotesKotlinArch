package com.castrec.stephane.noteskotlinsample.commons.adapter

import android.support.v7.widget.RecyclerView
import java.util.ArrayList
import java.util.List
/**
 * Created by sca on 24/02/2018.
 */
abstract class CustomBaseAdapter<T, V: RecyclerView.ViewHolder>(private val mValues: ArrayList<T>) : RecyclerView.Adapter<V>() {

    fun updateList(list: List<T>){
        mValues.clear();
        mValues.addAll(list)
        this.notifyDataSetChanged()
    }

}