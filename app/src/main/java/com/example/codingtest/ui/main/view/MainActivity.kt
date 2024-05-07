package com.example.codingtest.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingtest.data.api.ApiHelper
import com.example.codingtest.data.api.RetrofitBuilder
import com.example.codingtest.data.model.Articles
import com.example.codingtest.data.model.ResponseModel
import com.example.codingtest.databinding.ActivityMainBinding
import com.example.codingtest.ui.main.adapter.ArticleAdapter
import com.example.codingtest.ui.main.base.ViewModelFactory
import com.example.codingtest.ui.main.viewmodel.ArticleViewModel
import com.example.codingtest.utils.Constant.SHARE_URL
import com.example.codingtest.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var adapter: ArticleAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArticleAdapter(arrayListOf())
        binding.recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        articleViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        )[ArticleViewModel::class.java]
    }

    private fun setupObservers() {
        articleViewModel.getArticles().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
                        it.data?.let { articles ->
                            articles.body()?.let { it1 -> setAdapter(it1) }
                        }
                    }

                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }

                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }
    private fun setAdapter(articles: ResponseModel) {
        val arraylist = ArrayList<Articles>()
        arraylist.addAll(articles.articles)
        val articleAdapter = ArticleAdapter(arraylist)
        binding.recyclerView.adapter = articleAdapter
        articleAdapter.setOnClickListener(object :
            ArticleAdapter.OnClickListener {
            override fun onClick(position: Int, articles: Articles) {
                val intent = Intent(this@MainActivity, WebViewActivity::class.java)
                intent.putExtra(SHARE_URL, articles.url)
                startActivity(intent)
            }
        })
    }


}
