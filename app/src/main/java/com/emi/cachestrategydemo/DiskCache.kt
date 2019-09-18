package com.emi.cachestrategydemo

import android.content.Context
import com.jakewharton.disklrucache.DiskLruCache
import java.io.*

class DiskCache : CacheContext{


    private var mContext : Context?=null
    private lateinit var diskcache : DiskLruCache
    private  var memeCache = MemoryCache()

    override fun setContext(context: Context) {
        this.mContext = context
    }


    fun initDiskCache() {
            val directory = File(mContext!!.cacheDir, File.separator + "lru")
            val appVersion = 1
            val valueCount = 1
            val maxSize = 1024L * 1024L * 10L
            diskcache = DiskLruCache.open(directory, appVersion, valueCount, maxSize)
    }

    fun writeToCache(key : String, value : ArrayList<String>?){
            if(memeCache.readFromCache(key) == null){
                memeCache.writeCache(key, value!!)
            }

         writeDiskCache(key, value!!)
    }


   private fun bytetoCollectConverter(byte : ByteArray) : ArrayList<String> {
        val inputStream = ByteArrayInputStream(byte)
        val inputData = DataInputStream(inputStream)
        val dataList = ArrayList<String>()
        while(inputData.available() > 0){
            val result = inputData.readUTF()
            dataList.add(result)
        }
       return dataList
    }

   private fun writeDiskCache(key : String, value : ArrayList<String>) {
        val editor : DiskLruCache.Editor = diskcache.edit(key)
        editor.let {
            val bytes = collectionByteConverter(value)
            val output = editor.newOutputStream(0)
            output.write(bytes)
            it.commit()
        }
    }

    private fun collectionByteConverter(list : ArrayList<String>) : ByteArray{
        val byteCon = ByteArrayOutputStream()
        val dataStream = DataOutputStream(byteCon)
         list.forEach {
             dataStream.writeUTF(it)
         }
        return byteCon.toByteArray()
    }

    fun readFromCache(key : String) : ArrayList<String>{
        val memResult: ArrayList<String>?  = memeCache.readFromCache(key)
        val diskResult : ArrayList<String>? = readFromDiskCache(key)
        println(diskResult)
        return memResult ?: diskResult!!
    }

    private fun readFromDiskCache(key : String) : ArrayList<String>{
        var resultData : ArrayList<String>
        val snapshot : DiskLruCache.Snapshot = diskcache.get(key)
        snapshot.let {
            val input = it.getInputStream(0)
            val bytes = input.readBytes()
            resultData = bytetoCollectConverter(bytes)
            it.close()
        }
        return resultData
    }

}