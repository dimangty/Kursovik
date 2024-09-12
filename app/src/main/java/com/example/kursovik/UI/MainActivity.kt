package com.example.kursovik

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.kursovik.Core.Utils.ErrorService
import com.example.kursovik.Core.Utils.ProgresService
import com.example.kursovik.UI.LoginFragment
import com.example.kursovik.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @Inject lateinit var progressService: ProgresService
    @Inject lateinit var errorService: ErrorService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragment()
        setupUI()
    }

   fun setupFragment() {
       supportFragmentManager
           .beginTransaction()
           .replace(R.id.fragment_container, LoginFragment())
           .commit()
   }

    fun setupUI() {
        progressService.show.observe(this, Observer {
            //your code here
            if (it) {
                progressService.showLoadingDialog(this)
            } else {
                progressService.hideLoading()
            }
        })

        errorService.show.observe(this, Observer {
            //your code here
            if (it.isNotEmpty()) {
                errorService.show(it, this)
            }
        })


    }

    override fun onResume() {
        super.onResume()
    }


}