package com.rilodev.d2dapps.core.ui.baseview

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rilodev.d2dapps.core.utils.CustomDialog.loadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseViewActivity: AppCompatActivity() {
    private lateinit var _loadingDialog: AlertDialog

    fun showLoadingDialog() {
        _loadingDialog = loadingDialog()
        _loadingDialog.show()
    }

    fun dismissLoadingDialog() {
        _loadingDialog.dismiss()
    }
}