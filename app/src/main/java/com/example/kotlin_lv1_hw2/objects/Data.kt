package com.example.kotlin_lv1_hw2.objects

import com.google.gson.annotations.SerializedName

class Data<T> {
    @SerializedName("data")
    var data: T? = null
}
