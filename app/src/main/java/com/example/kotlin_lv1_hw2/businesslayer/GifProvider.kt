package com.example.kotlin_lv1_hw2.businesslayer

import com.example.kotlin_lv1_hw2.datalayer.IAccessor
import com.example.kotlin_lv1_hw2.objects.Gif

class GifProvider(val accessor: IAccessor) {
    suspend fun getGifs(offset: Int, limit: Int): List<Gif> {
        val gifs = accessor.getGifs(offset, limit)
        return gifs
    }
}