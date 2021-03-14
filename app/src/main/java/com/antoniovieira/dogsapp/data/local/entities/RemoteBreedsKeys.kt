package com.antoniovieira.dogsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds_remote_keys")
data class RemoteBreedsKeys(
    @PrimaryKey
    val breedId: String,
    val prevKey: Int?,
    val nextKey: Int?
)