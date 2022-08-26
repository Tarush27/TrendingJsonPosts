package com.tarush27.jsonposts.apiCall

import com.tarush27.jsonposts.apiConstants.POSTS_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit = Retrofit.Builder()
        .baseUrl(POSTS_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postService = retrofit.create(PostsService::class.java)


}