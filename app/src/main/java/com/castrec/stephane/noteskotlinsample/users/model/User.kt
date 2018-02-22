package com.castrec.stephane.noteskotlinsample.users.model

import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by sca on 18/02/2018.
 */
data class User(@SerializedName("date") @PrimaryKey val timestamp: Long,
                @SerializedName("username") val name: String,
                @SerializedName("urlPhoto") val photoUrl: String)