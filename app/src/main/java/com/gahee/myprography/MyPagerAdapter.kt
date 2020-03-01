package com.gahee.myprography

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.gahee.myprography.fragments.CallsFragment
import com.gahee.myprography.fragments.ChatsFragment
import com.gahee.myprography.fragments.ContactsFragment

class MyPagerAdapter(
    fragmentManager: FragmentManager,
    private val numOfPages: Int
) : FragmentStatePagerAdapter(
    fragmentManager, numOfPages
){

    override fun getItem(position: Int): Fragment {

        when(position){
            BottomNavState.CALL.ordinal -> return CallsFragment()
            BottomNavState.CHATS.ordinal -> return ChatsFragment()
            BottomNavState.CONTACTS.ordinal -> return ContactsFragment()
        }

        return CallsFragment()
    }

    override fun getCount(): Int {
        return numOfPages
    }

}