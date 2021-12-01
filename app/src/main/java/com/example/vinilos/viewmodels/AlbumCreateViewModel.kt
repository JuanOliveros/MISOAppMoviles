package com.example.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.repositories.AlbumCreateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumCreateViewModel(application: Application) :  AndroidViewModel(application) {

    private val albumCreateRepository = AlbumCreateRepository(application)

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    fun saveData(params: MutableMap<String, String>) {
        Log.i("Viewmodel", params.toString())
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var saveNewAlbum = albumCreateRepository.saveData(params)
                    Log.i("Http code result", saveNewAlbum.toString())
                }
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

}