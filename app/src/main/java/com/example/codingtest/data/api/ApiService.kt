package com.example.codingtest.data.api

import com.example.codingtest.data.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getArticle(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<ResponseModel>
}