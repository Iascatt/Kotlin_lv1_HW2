package com.example.kotlin_lv1_hw2.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlin_lv1_hw2.ServiceLocator
import com.example.kotlin_lv1_hw2.datalayer.DataSourceImpl
import com.example.kotlin_lv1_hw2.datalayer.GifPagingSource
import com.example.kotlin_lv1_hw2.objects.Gif
import kotlinx.coroutines.flow.Flow

class MainViewModel() : ViewModel() {
    private val dataSource = ServiceLocator.dataSource()

    suspend fun getGifs(): Flow<PagingData<Gif>> {
        Log.d("PAGING", "MVM: GG")
        return dataSource.getGifs().cachedIn(viewModelScope)
    }
}