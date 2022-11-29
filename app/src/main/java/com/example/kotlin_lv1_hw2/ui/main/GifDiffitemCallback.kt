package com.example.kotlin_lv1_hw2.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlin_lv1_hw2.objects.Gif

class GifDiffitemCallback: DiffUtil.ItemCallback<Gif>() {
    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem.images.original.url == newItem.images.original.url
    }
}