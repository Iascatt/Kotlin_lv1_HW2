package com.example.kotlin_lv1_hw2.datalayer

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlin_lv1_hw2.objects.Gif
import kotlinx.coroutines.flow.Flow

class DataSourceImpl (
    private val accessor: IAccessor
)  {

    suspend fun getGifs(): Flow<PagingData<Gif>> {
        Log.d("PAGING", "DSI")
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GifPagingSource(backend = accessor)
            }
        ).flow
    }
}