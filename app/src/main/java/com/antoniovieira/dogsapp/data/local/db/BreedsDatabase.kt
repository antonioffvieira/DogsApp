package com.antoniovieira.dogsapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.antoniovieira.dogsapp.data.local.converters.Converters
import com.antoniovieira.dogsapp.data.local.dao.BreedsDao
import com.antoniovieira.dogsapp.data.local.dao.BreedsRemoteKeysDao
import com.antoniovieira.dogsapp.data.local.entities.RemoteBreedsKeys
import com.antoniovieira.dogsapp.data.model.Breed

@Database(entities = [Breed::class, RemoteBreedsKeys::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BreedsDatabase : RoomDatabase() {

    abstract fun breedDao(): BreedsDao

    abstract fun breedRemoteKeysDao(): BreedsRemoteKeysDao

}