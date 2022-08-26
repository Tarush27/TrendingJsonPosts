package com.tarush27.jsonposts.apiCall

import com.google.gson.annotations.SerializedName


data class Post(
    @SerializedName("title") val title: String?,
    @SerializedName("body") val description: String?
)