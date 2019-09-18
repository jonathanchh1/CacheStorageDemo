package com.emi.cachestrategydemo

import android.content.Context
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File

class InternalStorage : CacheContext{

    private lateinit var mContext: Context

    override fun setContext(context: Context) {
        this.mContext = context
    }
    fun readFile(){
        val inputStream = getFilesDir().inputStream()
        val dataObjectStream = DataInputStream(inputStream)
        while (dataObjectStream.available() > 0){
            val result = dataObjectStream.readUTF()
        }
    }

    fun writeFile(data : ArrayList<String>){
        val outputStream = getFilesDir().outputStream()
        val dataObjectStream = DataOutputStream(outputStream)
        data.forEach {
        dataObjectStream.writeUTF(it)
        }
    }


    fun deleteFile(){
        val file = getFilesDir()
        if(file.exists()){
            file.delete()
        }
    }

    fun checkSpaceInStorage(){
        val totalSpace = getFilesDir().totalSpace
        val free = getFilesDir().freeSpace
    }


    fun listFilesInStorage(){
        val list = getFilesDir().listFiles()
    }

    private fun getFilesDir() : File{
        return File(mContext.packageName, "file.txt")
    }
}