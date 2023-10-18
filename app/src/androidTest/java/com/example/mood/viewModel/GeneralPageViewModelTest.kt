package com.example.mood.viewModel

import com.example.mood.fragment.GeneralPage
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GeneralPageViewModelTest{

    lateinit var testGeneralPageViewModel: GeneralPageViewModel

    @Before
    fun `init`(){
        testGeneralPageViewModel = GeneralPageViewModel()
    }

    @Test
    fun `addDB`(){
        assertTrue(testGeneralPageViewModel.addDB(GeneralPage().requireContext(), "", 0.0F, 0.0F, 0.0F, listOf(""),  listOf(""), listOf(""), listOf(""), 2, 5, 2, 5, 2, listOf(""), 0))
    }

}