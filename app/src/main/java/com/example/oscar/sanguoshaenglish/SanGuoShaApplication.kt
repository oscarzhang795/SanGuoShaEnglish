package com.example.oscar.sanguoshaenglish

import android.app.Application
import com.example.oscar.sanguoshaenglish.Entities.MyObjectBox
import io.objectbox.BoxStore

class SanGuoShaApplication: Application(){

    lateinit var boxStore: BoxStore

    override fun onCreate() {
        super.onCreate()
        boxStore = MyObjectBox.builder().androidContext(this).build()
    }
}