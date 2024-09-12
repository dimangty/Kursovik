package com.example.kursovik.UI

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.kursovik.Core.Domain.AuthorizationInfo
import com.example.kursovik.Obfuscator
import com.example.kursovik.R
import com.example.kursovik.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    @Inject
    lateinit var obfuscator: Obfuscator

    private lateinit var binding: FragmentLoginBinding
    val LOG_TAG = "Login log"
    var token: String? = null
    var userId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadLoginWindow()
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
                        showSecondFragment()
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

        val appid = obfuscator.reveal(Obfuscator.vkAPIkey)
        val url =
            "https://api.vk.com/oauth/authorize?client_id=$appid&scope=wall,audio,video,messages,friends,newsfeed,photos&redirect_uri=http://api.vk.com/blank.html&display=touch&response_type=token"
        binding.webView.loadUrl(url)

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

    fun showSecondFragment() {
        if (AuthorizationInfo.userId.isEmpty()) {
            if (userId != null) {
                AuthorizationInfo.userId = userId ?: ""
            }
        }

        if (AuthorizationInfo.token.isEmpty()) {
            if (token != null) {
                AuthorizationInfo.token = token ?: ""
            }
        }

        val transaction: FragmentTransaction =
            requireActivity().supportFragmentManager.beginTransaction()
        val anotherFragment = TabFragment()
        transaction.replace(R.id.fragment_container, anotherFragment)
        transaction.addToBackStack(null) // Optional: add to back stack if you want to allow users to navigate back

        transaction.commit()
    }

}