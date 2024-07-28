package com.example.kursovik.Core.Utils

import android.app.Dialog
import android.content.Context
import com.example.kursovik.R
import javax.inject.Inject

class ProgresService @Inject constructor() {
    private var loadingDialog: Dialog? = null
    private var context: Context? = null

    fun showLoadingDialog() {


        if (loadingDialog == null && context != null) {
            loadingDialog = Dialog(context!!)
            loadingDialog?.setContentView(R.layout.dialog_loading)
            loadingDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
            loadingDialog?.setCanceledOnTouchOutside(false)
        }
        loadingDialog?.show()
    }

    fun hideLoading() {
        loadingDialog?.hide()
    }

    fun setContext(context: Context?) {
        this.context = context
    }
}