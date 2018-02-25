package com.castrec.stephane.noteskotlinsample.commons.helper

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log


/**
 * Created by sca on 24/02/2018.
 */
class NetworkHelper {

    companion object {
        fun isInternetAvailable(context: Context): Boolean {
            try {
                val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val activeNetwork = cm.activeNetworkInfo
                return activeNetwork != null && activeNetwork.isConnectedOrConnecting
            } catch (e: Exception) {
                Log.e("HelloWorld", "Error on checking internet:", e)
            }

            //default allowed to access internet
            return true
        }
    }

}