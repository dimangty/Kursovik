package com.example.kursovik.Core.Utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class ErrorService @Inject constructor() {
    private var alert: AlertDialog? = null
    private val mShow = MutableLiveData<String>()
    val show = mShow

    fun show(message: String, context: Context?) {
        if (context == null) {
            return
        }

        val title = "Ошибка"
        if (alert == null) {
            alert = AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.cancel()
                }
                .create()

            alert?.setCancelable(false)
        }

        alert?.show()
    }
}