package com.emi.basic_preferences

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.emi.cachestrategydemo.R


class SettingsFragment  : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)


    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return when(preference?.key){
            "noti" -> {
                true
            }

            "alarm" -> {

                true
            }

            "volume" ->{

                true
            }
            else ->{
                super.onPreferenceTreeClick(preference)
            }
        }
    }
}