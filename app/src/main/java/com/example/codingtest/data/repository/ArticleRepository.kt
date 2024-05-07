package com.example.codingtest.data.repository

import com.example.codingtest.data.api.ApiHelper

class ArticleRepository(private val apiHelper: ApiHelper) {
    suspend fun getArticle(country: String, apiKey: String) = apiHelper.getArticle(country,apiKey)
}