package com.example.wikitest.view

import android.app.Activity
import android.app.Application

class WikiApplication : Application() {

    private var mCurrentActivity: Activity? = null

    init {
        instance = this
    }

    companion object {
        private var instance: WikiApplication? = null

        fun applicationContext(): WikiApplication {
            return instance as WikiApplication
        }
    }

    fun getCurrentActivity(): Activity? {
        return mCurrentActivity
    }

    fun setCurrentActivity(mCurrentActivity: Activity?) {
        this.mCurrentActivity = mCurrentActivity
    }

}