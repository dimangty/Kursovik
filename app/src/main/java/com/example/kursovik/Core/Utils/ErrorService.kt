package com.example.kursovik.Core.Utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import javax.inject.Inject

class ErrorService @Inject constructor() {
    private var alert: AlertDialog? = null
    private var context: Context? = null

    fun show(message: String) {
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

    fun setContext(context: Context?) {
        this.context = context
    }
}