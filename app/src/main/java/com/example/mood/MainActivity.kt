package com.example.mood

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import com.example.mood.db.MyDBHelper
import com.example.mood.fragment.GeneralPage
import com.example.mood.fragment.TabFragment

class MainActivity : AppCompatActivity() {

    companion object{
        var tabRecord : String? = null
        var tabStatistcs : String? = null
        var verySad: String? = null
        var sad: String? = null
        var lowSpirit: String? = null
        var neutral: String? = null
        var glad: String? = null
        var happy2: String? = null
        var veryHappy: String? = null

        var feelingAwful: String? = null
        var feelingBad: String? = null
        var feelingNotWell: String? = null
        var feelingNeutral: String? = null
        var feelingWell: String? = null
        var feelingGood: String? = null
        var feelingAwesome: String? = null

        var strongAnxiety: String? = null
        var anxiety: String? = null
        var worry: String? = null
        var neutral2: String? = null
        var unconcerned: String? = null
        var calm: String? = null
        var harmony: String? = null

        var mood: String? = null
        var health: String? = null
        var stress2: String? = null

        lateinit var helper: MyDBHelper
    }

    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //отключение темной темы
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        GeneralPage.tinyDB = TinyDB(this)

        //добавление фрагмента
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.containerView, TabFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
        tabRecord = this.getString(R.string.record)
        tabStatistcs = this.getString(R.string.statistics)

        verySad = this.getString(R.string.verySad)
        sad = this.getString(R.string.sad)
        lowSpirit = this.getString(R.string.lowSpirit)
        neutral = this.getString(R.string.neutral)
        glad = this.getString(R.string.glad)
        happy2 = this.getString(R.string.happy2)
        veryHappy = this.getString(R.string.veryHappy)

        feelingAwful = this.getString(R.string.feelingAwful)
        feelingBad = this.getString(R.string.feelingBad)
        feelingNotWell = this.getString(R.string.feelingNotWell)
        feelingNeutral = this.getString(R.string.feelingNeutral)
        feelingWell = this.getString(R.string.feelingWell)
        feelingGood = this.getString(R.string.feelingGood)
        feelingAwesome = this.getString(R.string.feelingAwesome)

        strongAnxiety = this.getString(R.string.strongAnxiety)
        anxiety = this.getString(R.string.anxiety)
        worry = this.getString(R.string.worry)
        neutral2 = this.getString(R.string.neutral2)
        unconcerned = this.getString(R.string.unconcerned)
        calm = this.getString(R.string.calm)
        harmony = this.getString(R.string.harmony)

        mood = this.getString(R.string.mood)
        health = this.getString(R.string.health)
        stress2 = this.getString(R.string.stress2)

        verifyStoragePermissions(this)
    }

    //выход при нажатии кнопки назад
    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    //запрос разрешений
    fun verifyStoragePermissions(activity: Activity?) {
        val permission =
            ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
            GeneralPage.tinyDB.putInt("idDB", 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        helper = MyDBHelper(this)
        helper.close()
    }

    override fun onResume() {
        super.onResume()
        helper = MyDBHelper(this)
        helper.readableDatabase
    }
}