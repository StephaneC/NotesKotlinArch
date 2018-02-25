package com.castrec.stephane.noteskotlinsample.commons

import android.content.Context
import com.castrec.stephane.noteskotlinsample.commons.exceptions.NoInternetException
import com.castrec.stephane.noteskotlinsample.commons.exceptions.TokenInvalidException
import com.castrec.stephane.noteskotlinsample.commons.helper.NetworkHelper
import okhttp3.Interceptor
import okhttp3.Response



/**
 * Created by sca on 24/02/2018.
 */
class ConnectivityInterceptor(val context: Context, val session: Session) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        if(!NetworkHelper.isInternetAvailable(context)){
            throw NoInternetException()
        }

        var response: Response  = chain!!.proceed(chain?.request()?.newBuilder()
                ?.header("token", session.getToken().token)?.build())
        if (response.code() == 401){
            throw TokenInvalidException()
        }
        return response
    }


}