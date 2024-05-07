package com.example.codingtest.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getArticle(country: String, apiKey: String) = apiService.getArticle(country, apiKey)
}