package com.emi.basic_preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.emi.cachestrategydemo.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, SettingsFragment())
            .commit()


    }


    fun readNoti() : Boolean{
        val sharedPref = getDefaultSharedPreferences(this)
        return sharedPref.getBoolean("noti", true)
    }

    fun readAlarm() : Boolean{
        val sharedPref = getDefaultSharedPreferences(this)
        return sharedPref.getBoolean("alarm", true)
    }

    fun getSharedPreferences(name : String) : SharedPreferences{ // can be utilized to share out of your activity or publicly as you wish
        return getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun getSharedPreferencesForActivity() : SharedPreferences{ //activity exclusive sharedPreferences
        return getPreferences(Context.MODE_PRIVATE)
    }

    fun write(key : String, value : Int) {
        val sharedPref = getSharedPreferences("com.emi.sharedPreferences")
        val editor = sharedPref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun read(key : String) : Int{
        val sharedPref = getSharedPreferences("com.emi.sharedPreferences")
         val default = 0
        return sharedPref.getInt(key, default)
    }
}
