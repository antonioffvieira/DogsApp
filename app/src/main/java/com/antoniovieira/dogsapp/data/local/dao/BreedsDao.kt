package com.antoniovieira.dogsapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antoniovieira.dogsapp.data.model.Breed

@Dao
interface BreedsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(breeds: List<Breed>)

    @Query("SELECT * FROM Breeds ORDER BY id ASC")
    fun selectAll(): PagingSource<Int, Breed>

    @Query("SELECT * FROM breeds WHERE name = :query")
    fun selectByBreedName(query: String): Breed

    @Query("DELETE FROM breeds")
    fun deleteBreeds()

}