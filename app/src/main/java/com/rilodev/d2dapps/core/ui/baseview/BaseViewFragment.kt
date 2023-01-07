package com.rilodev.d2dapps.core.ui.baseview

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.rilodev.d2dapps.core.utils.CustomDialog.loadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseViewFragment: Fragment() {
    private lateinit var _loadingDialog: AlertDialog

    fun showLoadingDialog() {
        _loadingDialog = requireActivity().loadingDialog()
        _loadingDialog.show()
    }

    fun dismissLoadingDialog() {
        _loadingDialog.dismiss()
    }
}