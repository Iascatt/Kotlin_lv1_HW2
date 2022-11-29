package com.example.kotlin_lv1_hw2.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import com.example.kotlin_lv1_hw2.R
import com.example.kotlin_lv1_hw2.objects.Gif

class GifAdapter: PagingDataAdapter<Gif, GifViewHolder>(GifDiffitemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        Log.d("PAGING", "GA: OCVH")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return GifViewHolder(view)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        Log.d("PAGING", "GA: OBVH")

        val gif = getItem(position)
        if (gif != null) {
            holder.bind(gif)
        }
    }
}