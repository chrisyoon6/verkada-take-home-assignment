package com.verkada.android.catpictures.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(PictureService.ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object PictureClient {
    val pictureService: PictureService by lazy {
        RetrofitClient.retrofit.create(PictureService::class.java)
    }
}