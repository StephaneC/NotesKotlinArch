package com.castrec.stephane.noteskotlinsample.commons

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.Toast
import com.castrec.stephane.noteskotlinsample.R
import com.castrec.stephane.noteskotlinsample.SigninActivity
import com.castrec.stephane.noteskotlinsample.commons.exceptions.NoInternetException
import com.castrec.stephane.noteskotlinsample.commons.exceptions.TokenInvalidException
import java.util.List

/**
 * Created by sca on 24/02/2018.
 */
abstract class BaseTabFragment : Fragment() {

    abstract fun getTitle() : Int
    abstract fun getIcon() : Int

    var emptyListStub : ViewStub? = null
    protected lateinit var mV:RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)

        mV = view.findViewById<RecyclerView>(R.id.list)

        return view
    }

    open fun manageError(error: Throwable?) {
        if(error is NoInternetException){
            //TODO
            Toast.makeText(this.activity, "No Internet Available", Toast.LENGTH_SHORT).show()
        } else if(error is TokenInvalidException){
            //back to signin
            val i = Intent(this.activity, SigninActivity::class.java)
            startActivity(i)
            this.activity!!.finish()
        }
    }

    /**
     * In charge of displaying state:
     * - No item
     * - Items
     */
    open fun manageListState(listSize: Int){
        if(listSize>0 && emptyListStub != null) {
            //there is item, displays it
            emptyListStub?.visibility = View.GONE
        } else {
            // no item. display stub
            if (emptyListStub == null) {
                //should be done once.
                emptyListStub = this.view!!.findViewById<ViewStub>(R.id.empty_stub)
                emptyListStub?.inflate()
            }
        }
    }

}