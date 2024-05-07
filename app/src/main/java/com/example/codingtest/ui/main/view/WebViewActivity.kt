package com.example.codingtest.ui.main.view

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.codingtest.databinding.ActivityWebviewBinding
import com.example.codingtest.utils.Constant

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra(Constant.SHARE_URL)) {
            url = intent.getStringExtra(Constant.SHARE_URL).toString()
        }
        setupWebView()
    }

    private fun setupWebView() {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(url)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack())
            binding.webView.goBack()
        else
            super.onBackPressed()
    }
}
