package com.example.kursovik.Core.Utils

import android.app.Dialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.kursovik.Core.Models.Poso.User
import com.example.kursovik.R
import javax.inject.Inject

class ProgresService  {
    private var loadingDialog: Dialog? = null

    private val mShow = MutableLiveData<Boolean>()
    val show = mShow

    fun showLoadingDialog(context: Context?) {
        if (loadingDialog == null && context != null) {
            loadingDialog = Dialog(context)
            loadingDialog?.setContentView(R.layout.dialog_loading)
            loadingDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
            loadingDialog?.setCanceledOnTouchOutside(false)
        }
        loadingDialog?.show()
    }

    fun hideLoading() {
        loadingDialog?.hide()
    }
}