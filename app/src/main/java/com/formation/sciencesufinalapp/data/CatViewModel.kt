package com.formation.sciencesufinalapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatViewModel(application: Application): AndroidViewModel(application){

    private val readAllData: LiveData<List<Cat>>
    private val repository: CatRepository

    init {
        val catDao = CatDatabase.getDatabase(application).catDao()
        repository = CatRepository(catDao)
        readAllData = repository.readAllData
    }

    fun addCat (cat: Cat){
        viewModelScope.launch(Dispatchers.IO){
            repository.addCat(cat)
        }
    }

}