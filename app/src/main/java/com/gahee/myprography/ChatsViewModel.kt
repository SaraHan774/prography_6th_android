package com.gahee.myprography

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gahee.myprography.FilmsApi.retrofitService
import kotlinx.coroutines.withContext
import java.lang.Exception

class ChatsViewModel : ViewModel() {

    private val _filmsLiveData : MutableLiveData<List<Film>> = MutableLiveData()

    val filmsLiveData : LiveData<List<Film>>
        get() = _filmsLiveData

    suspend fun fetchFilmsData(){
        try {
            val films = retrofitService.getFilms()

            _filmsLiveData.value = films

        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}
