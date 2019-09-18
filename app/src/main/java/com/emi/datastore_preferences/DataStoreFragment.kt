package com.emi.datastore_preferences

import android.os.Build
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.emi.cachestrategydemo.R
import com.emi.datastore_preferences.DataStore


class DataStoreFragment : PreferenceFragmentCompat(){

    private lateinit var dataStore : DataStore
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        dataStore = DataStore()
        dataStore.setContext(context!!)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val preference = findPreference<Preference>("noti")
            preference?.preferenceDataStore = dataStore //for individual Preference

            val manager = PreferenceManager(context)
            val datastore = manager.preferenceDataStore
             datastore?.putBoolean("demo", true)

        }
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return super.onPreferenceTreeClick(preference)
    }



}