package com.example.vinilos.viewmodels

import com.example.vinilos.models.Track
import com.example.vinilos.repositories.AlbumTracksRepository
import org.json.JSONObject

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumTrackViewModel(application: Application, albumId: Int) :  AndroidViewModel(application) {

    private val albumTracksRepository = AlbumTracksRepository(application)

    private val _createResult = MutableLiveData<Int>()

    val createResult: LiveData<Int>
        get() = _createResult

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = albumId

    fun saveData(params: MutableMap<String, String>, albumId: Int) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var saveNewAlbum = albumTracksRepository.saveData(params, albumId)
                    _createResult.postValue(saveNewAlbum)
                }
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    class Factory(val app: Application, val albumId: Int ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumTrackViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumTrackViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}