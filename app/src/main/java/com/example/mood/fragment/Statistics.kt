package com.example.mood.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mood.R
import com.example.mood.adapters.CardAdapter
import com.example.mood.databinding.StatisticsFragmentBinding
import com.example.mood.viewModel.StatisticsViewModel
import com.example.mood.viewModel.StatisticsViewModel.Companion.counterSts
import androidx.lifecycle.Observer

class Statistics : Fragment() {

    companion object {
        fun newInstance() = Statistics()
        val adapter = CardAdapter()
    }

    lateinit var viewModelSt: StatisticsViewModel
    lateinit var bindingStatistics: StatisticsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingStatistics = DataBindingUtil.inflate(inflater, R.layout.statistics_fragment,container,false)
        return bindingStatistics.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelSt = ViewModelProvider(this).get(StatisticsViewModel::class.java)

        //заполнение статистики
        bindingStatistics.apply {
            recyclerStatistics.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
            recyclerStatistics.adapter = adapter

            counterSts.observe(viewLifecycleOwner, Observer{
                if(counterSts.value == false){
                    txtOnbord.visibility = View.GONE
                }else{
                    txtOnbord.visibility = View.VISIBLE
                }
            })
        }
    }
}