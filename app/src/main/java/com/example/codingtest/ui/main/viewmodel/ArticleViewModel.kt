
package com.example.codingtest.ui.main.viewmodel

import androidx.lifecycle.*
import com.example.codingtest.data.repository.ArticleRepository
import com.example.codingtest.utils.Constant
import com.example.codingtest.utils.Resource
import kotlinx.coroutines.Dispatchers

class ArticleViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    fun getArticles() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = articleRepository.getArticle(Constant.US,Constant.API_KEY)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: Constant.ERROR_OCCURRED))
        }
    }
}