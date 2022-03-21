package com.sumie.glidelearning.glide.requestManager

class Key(path: String) {
    private var key: String = Tool.getSHA256StrJava(path)
    fun getKey(): String {
        return key
    }
    fun setKey(key: String) {
        this.key = key


    }
}