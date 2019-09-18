package com.emi.datastore_preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emi.cachestrategydemo.R
import javax.crypto.EncryptedPrivateKeyInfo

class PreferenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, DataStoreFragment())
            .commitNow()


    }

}
