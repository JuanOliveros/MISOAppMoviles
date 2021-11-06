package com.example.vinilos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GuestViewModel : ViewModel() {

    private val _title = MutableLiveData<String>().apply {
        value = "Bienvenido Invitado"
    }
    private val _content = MutableLiveData<String>().apply {
        value = "Bienvenido! Para acceder a las distinas opciones del app, haz click en el menu en la parte superior izquierda"
    }
    val title: LiveData<String> = _title
    val content: LiveData<String> = _content
}