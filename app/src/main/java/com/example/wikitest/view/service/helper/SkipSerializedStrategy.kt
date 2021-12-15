package com.example.wikitest.view.service.helper

import com.example.wikitest.view.annotation.SkipSerialization
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes


class SkipSerializedStrategy {

    companion object{
        fun getStrategy() : ExclusionStrategy {
            return object : ExclusionStrategy {
                override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                    return false
                }

                override fun shouldSkipField(f: FieldAttributes): Boolean {
                    return f.getAnnotation(SkipSerialization::class.java) != null
                }

            }
        }
    }

}