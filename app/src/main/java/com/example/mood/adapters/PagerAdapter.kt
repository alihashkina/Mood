package com.example.mood.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mood.MainActivity.Companion.tabRecord
import com.example.mood.MainActivity.Companion.tabStatistcs
import com.example.mood.fragment.GeneralPage
import com.example.mood.fragment.Statistics

class PagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                GeneralPage()
            }
            else -> {
                return Statistics()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return when (position){
            0 -> tabRecord
            else ->{
                return tabStatistcs
            }
        }
    }
}