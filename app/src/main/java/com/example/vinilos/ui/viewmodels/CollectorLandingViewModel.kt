package com.example.vinilos.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CollectorLandingViewModel : ViewModel() {

    private val _title = MutableLiveData<String>().apply {
        value = "¡Bienvenido amigo coleccionista!"
    }
    private val _content = MutableLiveData<String>().apply {
        value = "Para acceder a las distinas opciones, haz clic en el menú en la parte superior izquierda"
    }
    val title: LiveData<String> = _title
    val content: LiveData<String> = _content
}