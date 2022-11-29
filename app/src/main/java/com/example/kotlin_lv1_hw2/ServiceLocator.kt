package com.example.kotlin_lv1_hw2

import android.annotation.SuppressLint
import android.content.Context
import com.example.kotlin_lv1_hw2.businesslayer.GifProvider
import com.example.kotlin_lv1_hw2.datalayer.DataSourceImpl
import com.example.kotlin_lv1_hw2.datalayer.IAccessor

@SuppressLint("StaticFieldLeak")
object ServiceLocator {
    lateinit var context: Context

    private val accessor by lazy { IAccessor.create(context.getString(R.string.base_url))  }

    fun initialize(context: Context) {
        this.context = context.applicationContext
    }

    fun provider(): GifProvider {
        return GifProvider(accessor)
    }

    fun dataSource(): DataSourceImpl {
        return DataSourceImpl(accessor)
    }
}