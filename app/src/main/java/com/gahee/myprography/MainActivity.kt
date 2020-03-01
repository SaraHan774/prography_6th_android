package com.gahee.myprography

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG : String = "MainActivity"
        private const val NUM_OF_PAGES = 3
    }

    private lateinit var bottomNavViewModel : BottomNavViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavViewModel = ViewModelProviders.of(this).get(BottomNavViewModel::class.java)

        setUpViewPager()

        //setBottomNavState 에 의하여 바뀐 상태가 Observe 되고 순서에 맞게 버튼의 checked 상태를 바꾼다.
        bottomNavViewModel.bottomNavState.observe(this, Observer {
            bottom_nav_view.isSelected = true
            bottom_nav_view.menu.getItem(it.ordinal).isChecked = true
        })

        setUpBottomNavigationListener()
    }


    private fun setUpViewPager(){
        main_view_pager_container.adapter = MainPagerAdapter(supportFragmentManager, NUM_OF_PAGES)
        main_view_pager_container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                Log.d(TAG, "ViewPager Selected Page : $position")
                //Bottom Navigation View 의 State 를 LiveData 로 처리한다.
                //ViewPager 가 선택된 순서대로 BottomNavigationView 의 상태를 바꾸어준다.
                bottomNavViewModel.setBottomNavState(position)
            }

        })
    }


    private fun setUpBottomNavigationListener(){
        bottom_nav_view.setOnNavigationItemSelectedListener {

            Log.d(TAG, "bottomNav Menu Position : ${it.title}")

            when(it.itemId){
                R.id.navigation_call -> setViewPagerPosition(BottomNavState.CALL.ordinal)
                R.id.navigation_chat -> setViewPagerPosition(BottomNavState.CHATS.ordinal)
                R.id.navigation_contacts -> setViewPagerPosition(BottomNavState.CONTACTS.ordinal)
                else -> false
            }
        }
    }


    private fun setViewPagerPosition(position : Int) : Boolean{
        main_view_pager_container.currentItem = position
        Log.d(TAG, "ViewPager Position : $position")
        return true
    }


}
