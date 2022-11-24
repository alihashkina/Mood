package com.example.mood.viewModel

import android.content.ContentValues
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mood.MainActivity.Companion.helper
import com.example.mood.R
import com.example.mood.adapters.CardAdapter
import com.example.mood.adapters.CardAdapter.Companion.deleteCard
import com.example.mood.adapters.dataClass.Card
import com.example.mood.db.MyDBHelper
import com.example.mood.fragment.GeneralPage.Companion.bindingGeneralPage
import com.example.mood.fragment.GeneralPage.Companion.tinyDB
import com.example.mood.fragment.Statistics
import com.example.mood.viewModel.StatisticsViewModel.Companion.counterSts

class GeneralPageViewModel : ViewModel() {

    var counter: MutableLiveData<Int> = MutableLiveData(0)

    companion object{
        var dateDB = ""
        var arrayMoodGraph: ArrayList<Float> = arrayListOf()
        var arrayHealthGraph: ArrayList<Float> = arrayListOf()
        var arrayStressGraph: ArrayList<Float> = arrayListOf()
        var arrayDateGraph : MutableList<String> = mutableListOf()
    }

    lateinit var cards: Card
    var chipsHDB = ""
    var chipsUhDB = ""
    var chipsSDB = ""
    var chipsCDB = ""
    var chipsODB = ""
    var moodDB = 0.0F
    var healthDB = 0.0F
    var stressDB = 0.0F
    var idDB = 0
    var cv = ContentValues()

    fun addDB(context: Context, dateDB: String, moodDB: Float, healthDB: Float, stressDB: Float, chipsHealthyDB: String, chipsUnHealthyDB: String, chipsSymptomsDB: String, chipsCareDB: String, daysDB: Int, monthDB: Int, yearsDB: Int, hoursDB: Int, minuteDB: Int, chipsOtherDB: String, idDB: Int){

        cv.put("DATE", dateDB)
        cv.put("MOOD", moodDB)
        cv.put("HEALTH", healthDB)
        cv.put("STRESS", stressDB)
        cv.put("CHIPSHEALTHY", chipsHealthyDB)
        cv.put("CHIPSUNHEALTHY", chipsUnHealthyDB)
        cv.put("CHIPSSYMPTOMS", chipsSymptomsDB)
        cv.put("CHIPSCARE", chipsCareDB)
        cv.put("DAYS", daysDB)
        cv.put("MONTH", monthDB)
        cv.put("YEARS", yearsDB)
        cv.put("HOURS", hoursDB)
        cv.put("MINUTE", minuteDB)
        cv.put("CHIPSOTHER", chipsOtherDB)
        cv.put("ID", idDB)

        MyDBHelper(context).writableDatabase.insert("USERS", null, cv)
    }

    fun readDB(){
        var db = helper.readableDatabase
        var rs = db.rawQuery(
            "SELECT DATE, MOOD, HEALTH, STRESS, CHIPSHEALTHY, CHIPSUNHEALTHY, CHIPSSYMPTOMS, CHIPSCARE, CHIPSOTHER, DAYS, MONTH, YEARS, HOURS, MINUTE, ID FROM USERS ORDER BY YEARS, MONTH, DAYS, HOURS, MINUTE ASC",
            null
        )

        arrayDateGraph = arrayListOf()
        arrayMoodGraph = arrayListOf()
        arrayHealthGraph = arrayListOf()
        arrayStressGraph = arrayListOf()

        if(!deleteCard){
            Statistics.adapter.update()
        }

        while (rs != null && rs.getCount() > 0 && rs.moveToNext()) {
            dateDB = rs.getString(0)
            moodDB = rs.getString(1).toFloat()
            healthDB = rs.getString(2).toFloat()
            stressDB = rs.getString(3).toFloat()
            chipsHDB = rs.getString(4).replace(",", " | ")
            chipsUhDB = rs.getString(5).replace(",", " | ")
            chipsSDB = rs.getString(6).replace(",", " | ")
            chipsCDB = rs.getString(7).replace(",", " | ")
            chipsODB = rs.getString(8).replace(",", " | ")
            idDB = rs.getString(14).toInt()

            tinyDB.putString("DateDB", "+")

            arrayDateGraph.add(dateDB)
            arrayMoodGraph.add(moodDB)
            arrayHealthGraph.add(healthDB)
            arrayStressGraph.add(stressDB)

            if (!deleteCard) {
                cards = Card(
                    dateDB,
                    chipsHDB,
                    chipsUhDB,
                    chipsSDB,
                    chipsCDB,
                    moodDB,
                    healthDB,
                    stressDB,
                    chipsODB,
                    idDB
                )
                Statistics.adapter.addCard(cards)
            }

            counter.value?.let {
                counter.value = it + 1
            }
        }

    }

    fun deleteAllDB(context: Context){
        Toast.makeText(context, context.getString(R.string.toastDelete), Toast.LENGTH_SHORT).show()
        helper.writableDatabase.delete("USERS", null, null)
        bindingGeneralPage.scrollGraph.visibility = View.GONE
        bindingGeneralPage.txtOnbord.visibility = View.VISIBLE
        counterSts.value?.let {
            counterSts.value = true
        }
        readDB()
    }

    fun deleteCardDB(context: Context){
        Toast.makeText(context, context.getString(R.string.toastDelete), Toast.LENGTH_SHORT).show()
        helper.writableDatabase.delete("USERS", "USERID=${CardAdapter.idDB}", null)
        readDB()
        bindingGeneralPage.deleteCard.callOnClick()
    }
}