package com.antoniovieira.dogsapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breed(
    val id: String,
    val name: String,
    val origin: String?,
    @SerializedName("breed_group")
    val group: String?,
    val image: Image,
    val temperament: String
) : Parcelable