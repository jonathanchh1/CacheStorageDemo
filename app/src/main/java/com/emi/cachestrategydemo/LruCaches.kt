package com.emi.cachestrategydemo
class LruCaches{

    val hashmap : HashMap<Int, Entry> = HashMap()
    private var start : Entry?=null
    private var end : Entry?=null
    private var LRU_SIZE = 4

    fun getEntry(key : Int?) : Int{
        if(hashmap.containsKey(key)){
            val entry : Entry? = hashmap[key]
            removeNode(entry)
            addTop(entry)
            return entry?.value!!
        }
        return -1
    }

    fun putEntry(key : Int, value : Int){
        if(hashmap.containsKey(key)){

            val entry : Entry? = hashmap[key]
             entry?.value = value
            removeNode(entry)
            addTop(entry)
        }else{
            val newNode = Entry()
            newNode.left = null
            newNode.right = start
            newNode.value = value
            newNode.key = key

            if(hashmap.size > LRU_SIZE){
                hashmap.remove(end?.key)
                removeNode(end)
                addTop(newNode)
            }else{
                addTop(newNode)
            }
            hashmap[key] = newNode
        }
    }


   private fun addTop(node : Entry?){
        node?.right = start
        node?.left = null
        if(start != null){
            start?.left = node
        }else{
            start = node
        }

        if(end == null){
            end = start
        }
    }

    private fun removeNode(node : Entry?){
        if(node?.left != null){
            node.left!!.right = node.right
        }else{
            start = node?.right
        }

        if(node?.right != null){
            node.right!!.left = node.left
        }else{
            end = node?.left
        }
    }
}
