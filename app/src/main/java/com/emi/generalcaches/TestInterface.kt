package com.emi.generalcaches

import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream

class TestInterface : Trucks, SecondTestInterface{


    override fun fetchArtist(text: String, age: Int): Any {
       return "anything"
    }

    val data = Models(title = "mecedez", desc = "classic and fast", model = 2020)
    override fun set(text: String): String {
          return text.plus("more")
    }
    override fun fetchData() = data



    companion object{

        @JvmStatic fun main(args : Array<String>) {

            val foo = TestInterface()
            val bab = foo.set("its like")

            println(bab)
            val dataFetch = foo.fetchData()

            val dataList = ArrayList<String>()
            dataList.add("phones")
            dataList.add("cars")
            dataList.add("rings")
            dataList.add("farms")

        }
    }
}