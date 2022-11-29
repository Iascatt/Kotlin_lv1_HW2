package com.example.kotlin_lv1_hw2.ui.main

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_lv1_hw2.R
import com.example.kotlin_lv1_hw2.ServiceLocator.context
import com.example.kotlin_lv1_hw2.objects.Gif
import com.squareup.picasso.Picasso

class GifViewHolder(view: View): RecyclerView.ViewHolder(view) {
    protected val image by lazy { view.findViewById<ImageView>(R.id.image) }

    fun bind(gif: Gif) {
        val url = gif.images.original.url
        Log.d("PAGING", "GVH BIND: url=${url}")
        Glide.with(context).load(url).into(image)
    }
}