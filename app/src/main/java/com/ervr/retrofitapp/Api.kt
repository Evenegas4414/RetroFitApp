package com.ervr.retrofitapp

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("realestate")
    fun getItems(): Call<List<Item>>
}
