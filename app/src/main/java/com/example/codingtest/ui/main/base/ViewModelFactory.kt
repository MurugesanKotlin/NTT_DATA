package com.example.codingtest.ui.main.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codingtest.data.api.ApiHelper
import com.example.codingtest.data.repository.ArticleRepository
import com.example.codingtest.ui.main.viewmodel.ArticleViewModel
import com.example.codingtest.utils.Constant

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(ArticleRepository(apiHelper)) as T
        }
        throw IllegalArgumentException(Constant.UNKNOWN_CLASS)
    }

}

