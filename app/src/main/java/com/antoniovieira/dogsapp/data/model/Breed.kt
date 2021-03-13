package com.antoniovieira.dogsapp.data.model

import com.google.gson.annotations.SerializedName

data class Breed(
    val id: String,
    val name: String,
    val origin: String?,
    @SerializedName("breed_group")
    val group: String?,
    val image: Image,
    val temperament: String
)