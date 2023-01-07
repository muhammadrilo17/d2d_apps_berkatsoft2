package com.rilodev.d2dapps.core.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.rilodev.d2dapps.databinding.LogoutDialogBinding
import com.rilodev.d2dapps.databinding.PopupDetailCurrentTaskBinding
import com.rilodev.d2dapps.databinding.PopupLoadingDialogBinding

object CustomDialog {
    fun Activity.loadingDialog(): AlertDialog {
        val builder = AlertDialog.Builder(this).create()
        val view = PopupLoadingDialogBinding.inflate(layoutInflater)

        builder.setView(view.root)
        builder.setCancelable(false)
        return builder
    }

    fun Activity.confirmExitDialog(
        instructionText: String = "",
        destination: Class<*>? = null,
        action: (() -> Unit) = {}
    ): AlertDialog {
        val builder = AlertDialog.Builder(this).create()
        val view = LogoutDialogBinding.inflate(layoutInflater)

        if (instructionText.isNotEmpty()) view.instruction.text = instructionText
        view.btnNo.setOnClickListener { builder.dismiss() }
        view.btnYes.setOnClickListener {
            if (destination != null) {
                val intent = Intent(this, destination)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                action()
                startActivity(intent)
            }
            finishAndRemoveTask()
        }

        builder.setView(view.root)
        builder.setCancelable(true)
        builder.show()
        return builder
    }

    fun Activity.taskDetailDialog(
        currentDate: String,
        description: String,
        showButton: Boolean = false,
        action: (() -> Unit) = {}
    ): AlertDialog {
        val builder = AlertDialog.Builder(this).create()
        val view = PopupDetailCurrentTaskBinding.inflate(layoutInflater)

        if(showButton){
            view.btnEndTask.visibility = View.VISIBLE
        } else {
            view.btnEndTask.visibility = View.GONE
        }

        view.currentDate.text = currentDate
        view.description.text = description

        view.btnEndTask.setOnClickListener {
            action()
            builder.dismiss()
        }

        builder.setView(view.root)
        builder.setCancelable(true)
        builder.show()
        return builder
    }
}