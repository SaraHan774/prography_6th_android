package com.gahee.myprography.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class BottomNavState{
    CALL,
    CHATS,
    CONTACTS
}

class BottomNavViewModel : ViewModel(){

    private var _bottomNavState : MutableLiveData<BottomNavState> = MutableLiveData()
    val bottomNavState : LiveData<BottomNavState>
        get() = _bottomNavState


    fun setBottomNavState(position : Int){
        when(position){
            BottomNavState.CALL.ordinal ->
                _bottomNavState.value =
                    BottomNavState.CALL
            BottomNavState.CHATS.ordinal ->
                _bottomNavState.value =
                    BottomNavState.CHATS
            BottomNavState.CONTACTS.ordinal ->
                _bottomNavState.value =
                    BottomNavState.CONTACTS
        }
    }

}