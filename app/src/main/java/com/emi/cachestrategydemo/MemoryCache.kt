package com.emi.cachestrategydemo

import android.content.Context
import android.util.LruCache
import java.io.File


class MemoryCache  {


    private lateinit var memCache : LruCache<String, ArrayList<String>>
    private val maxMemory = (Runtime.getRuntime().maxMemory()/1024).toInt()

    init {
        initMemoryCache()
    }

    fun initMemoryCache() {
        memCache = object : LruCache<String, ArrayList<String>>(maxMemory){
            override fun sizeOf(key: String?, value: ArrayList<String>?): Int {
                 var data : String?=null
                 value?.forEach {
                     data = it
                 }
                return data!!.toByteArray().size
            }
        }
    }

    fun writeCache(key : String?, dataList : ArrayList<String>){
          memCache.put(key, dataList)
    }


    fun readFromCache(key : String?) : ArrayList<String>? {
         return when(key){
             key -> memCache.get(key)
             else -> null
         }
    }

}