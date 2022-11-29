package com.example.kotlin_lv1_hw2

import android.app.Application


class GifApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ServiceLocator.initialize(this)
    }
}