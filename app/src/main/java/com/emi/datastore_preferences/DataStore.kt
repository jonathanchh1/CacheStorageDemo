package com.emi.datastore_preferences


import android.content.Context
import androidx.preference.PreferenceDataStore
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class DataStore : PreferenceDataStore(), DataContext {

    //you'll be storing data with this and calling it from your
    private var executor : ExecutorService = Executors.newSingleThreadExecutor()
    private lateinit var mContext: Context
    private val sharedPref = mContext.getSharedPreferences(mContext.packageName, Context.MODE_PRIVATE)
    override fun putBoolean(key: String?, value: Boolean) {
        val edit = sharedPref.edit()
        edit.putBoolean(key, value)
        edit.apply()
   }

    override fun setContext(context: Context) {
        this.mContext = context
    }

    //you'll be storing data
    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return  sharedPref.getBoolean(key, defValue)
    }


}