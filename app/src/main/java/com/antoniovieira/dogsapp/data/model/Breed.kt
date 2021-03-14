package com.antoniovieira.dogsapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Breeds")
@Parcelize
data class Breed(
    @PrimaryKey
    val id: String,
    val name: String,
    val origin: String?,
    @SerializedName("breed_group")
    val group: String?,
    val image: Image?,
    val temperament: String
) : Parcelable