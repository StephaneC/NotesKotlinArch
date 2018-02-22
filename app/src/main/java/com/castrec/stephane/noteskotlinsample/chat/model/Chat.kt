package com.castrec.stephane.noteskotlinsample.chat.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by sca on 18/02/2018.
 */
@Entity(tableName = "chats")
data class Chat(@SerializedName("id") @PrimaryKey val id: String,
                @SerializedName("date") @ColumnInfo(name = "date") val timestamp: Long,
                @SerializedName("username") @ColumnInfo(name = "username") val name: String,
                @SerializedName("message") @ColumnInfo(name = "message") val message: String)