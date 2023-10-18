package com.example.mood.fragment

import android.widget.TextView
import com.example.mood.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GeneralPageTest{

    lateinit var testGeneralPage: GeneralPage

    @Before
    fun `init`(){
        testGeneralPage = GeneralPage()
    }

//    @Test
//    fun `txtRecordIsNotEmpty`(){
//        var testTxtRecord = testGeneralPage.view!!.findViewById<TextView>(R.id.txtRecord)
//        assertTrue(testTxtRecord.text.isNullOrBlank())
//    }
//
//    @Test
//    fun `txtOnbordH6IsNotEmpty`(){
//        var testTxtOnbordH6 = testGeneralPage.view!!.findViewById<TextView>(R.id.txtOnbordH6)
//        assertTrue(testTxtOnbordH6.text.isNullOrBlank())
//    }
//
//    @Test
//    fun `txtOnbordB1IsNotEmpty`(){
//        var testTxtOnbordB1 = testGeneralPage.view!!.findViewById<TextView>(R.id.txtOnbordB1)
//        assertTrue(testTxtOnbordB1.text.isNullOrBlank())
//    }
//
//    @Test
//    fun `btnSaveIsNotEmpty`(){
//        var testBtnSave = testGeneralPage.view!!.findViewById<ExtendedFloatingActionButton>(R.id.btnSave)
//        assertTrue(testBtnSave.text.isNullOrBlank())
//    }
//
//    @Test
//    fun `txtMoodIsMood`(){
//        var testTxtMood = testGeneralPage.view!!.findViewById<TextView>(R.id.txtMood)
//        var testTextMood = testGeneralPage.view!!.resources!!.getString(R.string.mood)
//        assertEquals(testTextMood, testTxtMood.text)
//    }
}