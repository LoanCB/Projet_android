package com.formation.sciencesufinalapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCat(cat: Cat)

    @Query("SELECT * FROM cat_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Cat>>
}