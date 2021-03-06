package com.example.challenge2.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.challenge2.Fragment.NewsFragment
import com.example.challenge2.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    private var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        url = intent.getStringExtra("abc")
        getUrl()
        backF.setOnClickListener { backFragment() }
    }

    private fun backFragment() {
        val intent = Intent(this, NewsFragment::class.java)
        startActivity(intent)
    }

    private fun getUrl() {
        webNews.loadUrl(url)
    }

}
