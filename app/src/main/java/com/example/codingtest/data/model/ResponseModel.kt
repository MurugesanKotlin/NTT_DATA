package com.example.codingtest.data.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("status") var status: String? = null,
    @SerializedName("totalResults") var totalResults: Int? = null,
    @SerializedName("articles") var articles: ArrayList<Articles> = arrayListOf()
)