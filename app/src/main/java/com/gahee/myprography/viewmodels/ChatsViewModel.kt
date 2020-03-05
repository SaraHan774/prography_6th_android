package com.gahee.myprography.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gahee.myprography.network.Film
import com.gahee.myprography.network.FilmsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception


enum class NetworkState{
    LOADING, DONE, ERROR
}

class ChatsViewModel : ViewModel() {

    private val _filmsLiveData : MutableLiveData<List<Film>> = MutableLiveData()
    val filmsLiveData : LiveData<List<Film>>
        get() = _filmsLiveData

    private val _networkState : MutableLiveData<NetworkState> = MutableLiveData()
    val networkState : LiveData<NetworkState>
        get() = _networkState

    private var viewModelJob = Job()

    //Retrofit 은 내부적으로 백그라운드 스레드에서 돌아가기 때문에 Main Dispatcher 를 사용해
    //Live Data 를 쉽게 업데이트 하도록 하였습니다.
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private suspend fun fetchFilmsDataFromNetwork(){
        try {
            _networkState.value = NetworkState.LOADING
            val films = FilmsApi.retrofitService.getFilms()
            _filmsLiveData.value = films
            _networkState.value = NetworkState.DONE
        }catch (e : Exception){
            e.printStackTrace()
            _networkState.value = NetworkState.ERROR
        }
    }

    //만약 이미 가져온 정보가 있다면 해당 정보를 사용한다.
    fun fetchFilmsData(){
        if(_filmsLiveData.value.isNullOrEmpty()){
            uiScope.launch {
                fetchFilmsDataFromNetwork()
            }
        }else{
            _filmsLiveData.value = filmsLiveData.value
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
