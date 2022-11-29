package com.example.kotlin_lv1_hw2.objects

import com.google.gson.annotations.SerializedName

class Gif {
    @SerializedName("id")
    var id = ""
    @SerializedName("url")
    var url = ""
    @SerializedName("images")
    lateinit var images: Images
}