package com.castrec.stephane.noteskotlinsample.users.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by sca on 18/02/2018.
 */
@Entity(tableName = "users")
data class User(@SerializedName("date") @PrimaryKey val timestamp: Long,
                @SerializedName("username") @ColumnInfo(name = "username") val name: String,
                @SerializedName("urlPhoto") @ColumnInfo(name = "urlPhoto") val photoUrl: String)