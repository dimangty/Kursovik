package com.example.kursovik

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.kursovik.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    private val appid = 3806178
    val LOG_TAG = "Login log"
    var token: String? = null
    var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        loadLoginWindow()
    val obfuscator = Obfuscator()
        val key = obfuscator.bytesByObfuscatingString("9f3b3102ab704b7c9a874ee92cdb288f")
        obfuscator.reveal(key)
    }

    fun loadLoginWindow() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.allowContentAccess = true

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Log.d(LOG_TAG, "WebStart")
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)

                Log.d(LOG_TAG, "Error")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.d(LOG_TAG, "WebFinish")
                Log.d(LOG_TAG, "Url=" + view?.url)
                if (url != null) {
                    if (url.contains("access_token")) {
                        token = getToken(url)
                        userId = getUserId(url)
                        Log.d(LOG_TAG, "Token=$token")
                        Log.d(LOG_TAG, "User=$userId")
                        showSecondActivity()
                    }
                }
            }

            @Suppress("deprecation")
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                view.loadUrl(url!!)
                Log.i(LOG_TAG, "loading: deprecation")
                return true
            }

            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                view.loadUrl(request.url.toString())
                Log.i(LOG_TAG, "loading: build.VERSION_CODES.N")
                return true
                //return super.shouldOverrideUrlLoading(view, request);
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                super.onReceivedSslError(view, handler, error)
                handler?.proceed()
            }
        }
        val url =
            "https://api.vk.com/oauth/authorize?client_id=$appid&scope=wall,audio,video,messages&redirect_uri=http://api.vk.com/blank.html&display=touch&response_type=token"
        binding.webView.loadUrl(url)

    }

    fun load2() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://example.com")
    }

    fun getToken(URL: String): String {
        var bufUrl = URL
        bufUrl = bufUrl.substring(URL.indexOf("access_token"))
        bufUrl = bufUrl.substring(bufUrl.indexOf("=") + 1)
        bufUrl = bufUrl.substring(0, bufUrl.indexOf("&"))
        return bufUrl
    }

    fun getUserId(URL: String): String {
        val strs =
            URL.split("=".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
        return strs[strs.size - 1]
    }

    fun showSecondActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("id", userId)
        intent.putExtra("token", token)
        startActivity(intent)

    }


}