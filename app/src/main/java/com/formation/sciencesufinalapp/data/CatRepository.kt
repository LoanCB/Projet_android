package com.formation.sciencesufinalapp.data

import androidx.lifecycle.LiveData

class CatRepository (private val catDao: CatDao ) {

    val readAllData: LiveData<List<Cat>> = catDao.readAllData()

    suspend fun addCat(cat: Cat){
        catDao.addCat(cat)
    }

}