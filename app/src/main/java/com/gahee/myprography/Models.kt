package com.gahee.myprography

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Film(
    val id : String,
    val title : String,
    val description : String,
    val director : String,
    val producer : String,

    @SerializedName("release_date")
    val releaseDate : String,

    @SerializedName("rt_score")
    val rate : String
) : Parcelable
