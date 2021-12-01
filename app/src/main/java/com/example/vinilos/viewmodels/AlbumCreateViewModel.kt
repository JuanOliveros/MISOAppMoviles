package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.repositories.AlbumCreateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumCreateViewModel(application: Application) :  AndroidViewModel(application) {

    private val albumCreateRepository = AlbumCreateRepository(application)

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    fun saveData(params: MutableMap<String, String>) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    albumCreateRepository.saveData(params)
                }
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

}