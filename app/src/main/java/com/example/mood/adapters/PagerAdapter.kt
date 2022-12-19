package com.example.mood.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mood.MainActivity.Companion.tabRecord
import com.example.mood.MainActivity.Companion.tabStatistcs
import com.example.mood.R
import com.example.mood.fragment.GeneralPage
import com.example.mood.fragment.Statistics

class PagerAdapter (fm: FragmentManager,context: Context) : FragmentPagerAdapter(fm) {

    val tabs: List<Pair<String, Fragment>> = listOf(
        context.resources.getString(R.string.record) to GeneralPage.newInstance(),
        context.resources.getString(R.string.statistics) to Statistics.newInstance()
    )

    override fun getItem(position: Int): Fragment {
        return tabs[position].second
    }

    override fun getCount(): Int {
        return tabs.count()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position].first
    }

}