package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.repositories.AlbumCreateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumCreateViewModel(application: Application) :  AndroidViewModel(application) {

    private val albumCreateRepository = AlbumCreateRepository(application)

    private val _createResult = MutableLiveData<Int>()

    val createResult: LiveData<Int>
        get() = _createResult

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    fun saveData(params: MutableMap<String, String>) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var saveNewAlbum = albumCreateRepository.saveData(params)
                    _createResult.postValue(saveNewAlbum)
                }
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumCreateViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumCreateViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}