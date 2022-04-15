package com.formation.sciencesufinalapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_table")
data class Cat (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String

)