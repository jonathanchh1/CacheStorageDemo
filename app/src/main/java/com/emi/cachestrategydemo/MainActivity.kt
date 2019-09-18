package com.emi.cachestrategydemo

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(){

    private val musicList = ArrayList<String>()
    private lateinit var diskcache : DiskCache
    private lateinit var memoryCache: MemoryCache

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        musicList.add("nigerian music")
        musicList.add("afrobeats")
        musicList.add("afrofusion")
        diskcache = DiskCache()
        diskcache.setContext(this)
        memoryCache = MemoryCache()
        diskcache.initDiskCache()
        diskcache.writeToCache("music", musicList)
        readData("music")
    }


    private fun readData(key : String){
       val result =  diskcache.readFromCache(key)
        println(result)
    }

}
