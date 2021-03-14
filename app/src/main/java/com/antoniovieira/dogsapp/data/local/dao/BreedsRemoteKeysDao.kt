package com.antoniovieira.dogsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antoniovieira.dogsapp.data.local.entities.RemoteBreedsKeys

@Dao
interface BreedsRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteBreedsKey: List<RemoteBreedsKeys>)

    @Query("SELECT * FROM breeds_remote_keys WHERE breedId = :id")
    fun selectByBreedId(id: String): RemoteBreedsKeys

    @Query("DELETE FROM breeds_remote_keys")
    fun deleteRemoteKeys()

}