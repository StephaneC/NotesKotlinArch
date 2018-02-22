package com.castrec.stephane.noteskotlinsample.notes.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by sca on 18/02/2018.
 */
@Entity(tableName = "notes")
data class Note(@SerializedName("id") @PrimaryKey val id: String,
                @SerializedName("date") @ColumnInfo(name = "date") val timestamp: Long,
                @SerializedName("username") @ColumnInfo(name = "username") val name: String,
                @SerializedName("note") @ColumnInfo(name = "message") val message: String,
                @SerializedName("done") @ColumnInfo(name = "done") val done: Boolean)