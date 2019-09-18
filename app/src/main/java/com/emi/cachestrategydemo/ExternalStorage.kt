package com.emi.cachestrategydemo

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import androidx.core.content.ContextCompat
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File

class ExternalStorage : CacheContext{

    private lateinit var mContext: Context

    override fun setContext(context: Context) {
        this.mContext = context
    }

    fun fetchExternalCache(data : ArrayList<String>){
        if(hasPermissions()){
            if (isWritable()){
                val file = privateDirectoryFile()
                 val outputStream = file?.outputStream()
                 val dataObjectStream = DataOutputStream(outputStream)
                  data.forEach {
                      dataObjectStream.writeUTF(it)
                  }

            }else if(isReadable()){
                val file = privateDirectoryFile()
                 val inputStream = file?.inputStream()
                 val dataObjectStream = DataInputStream(inputStream!!)
                 while (dataObjectStream.available() > 0){
                     val result = dataObjectStream.readUTF()
                 }
            }
        }
    }

    private fun privateDirectoryFile() : File? {
        val file = File(Environment.DIRECTORY_DOCUMENTS, "private.text")
        if(file.mkdirs()){
            file.createNewFile()
        }
        return file
    }

    fun listExternalDirectory(data : ArrayList<String>){
        val list = mContext.getExternalFilesDirs(null)
        if(list[0] != null){
            val file = list[0]
            val outputStream = file.outputStream()
            val dataObjectStream = DataOutputStream(outputStream)
            data.forEach {
                dataObjectStream.writeUTF(it)
            }

        }else if(list[1] != null){
            val secondFile = list[1]
            ///can be implemented here as well the same way that diskcache is done
        }

    }


    private fun hasPermissions() : Boolean {
       val granted: Int? = PackageManager.PERMISSION_GRANTED
      val permission : Int? = ContextCompat.checkSelfPermission(mContext, Manifest.permission_group.STORAGE)
      return permission ==  granted ?: PackageManager.PERMISSION_DENIED
    }

    private fun isWritable() : Boolean{
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun isReadable() : Boolean{
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED,Environment.MEDIA_MOUNTED_READ_ONLY)
    }
}