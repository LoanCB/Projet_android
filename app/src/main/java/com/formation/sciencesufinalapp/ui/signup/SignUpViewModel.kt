package com.formation.sciencesufinalapp.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private var _currentPlayer = MutableLiveData<String>("unknown")
    val currentPlayer: LiveData<String>
        get() = _currentPlayer

    fun updatePlayerName(name : String){
        _currentPlayer.value = name

    }


}