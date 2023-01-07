package com.rilodev.d2dapps.pages.auth.login

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.rilodev.d2dapps.core.utils.Utils.movePage
import com.rilodev.d2dapps.core.utils.Utils.setShowHidePassword
import com.rilodev.d2dapps.databinding.ActivityLoginBinding
import com.rilodev.d2dapps.pages.auth.register.RegisterActivity
import com.rilodev.d2dapps.pages.main.MainActivity
import com.rilodev.d2dapps.core.data.Resource
import com.rilodev.d2dapps.core.ui.baseview.BaseViewActivity
import com.rilodev.d2dapps.core.utils.Utils.movePageAndRemoveTask
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class LoginActivity : BaseViewActivity() {
    private val viewModel by viewModels<LoginViewModel>()
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private var isPasswordLoginHide = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        isLoggedIn()
        showHidePasswordSetup()

        buttonSignInListener()
        registerListener()
        binding.loginResult.visibility = View.GONE
    }

    private fun isLoggedIn() {
        viewModel.isLoggedIn().observe(this) { result ->
            if (result) {
                showLoadingDialog()
                Timer().schedule(delay = 2000) {
                    dismissLoadingDialog()
                    movePageAndRemoveTask(MainActivity::class.java)
                }
            }
        }
    }

    private fun showHidePasswordSetup() {
        with(binding.inputPassword) {
            btnShowHide.setOnClickListener {
                isPasswordLoginHide = setShowHidePassword(
                    inputPassword,
                    isPasswordLoginHide,
                    btnShowHide
                )
            }
        }
    }

    private fun buttonSignInListener() {
        binding.btnSignIn.setOnClickListener {
            if (binding.inputEmail.isEmailRight && binding.inputPassword.inputPassword.isPasswordRight) {
                login(binding.inputEmail.value, binding.inputPassword.inputPassword.value)
            } else {
                if (!binding.inputEmail.isEmailRight) binding.inputEmail.triggerEmptyField()
                else binding.inputPassword.inputPassword.triggerEmptyField()
            }
        }
    }

    private fun login(email: String, password: String) {
        binding.loginResult.visibility = View.GONE
        if (binding.inputEmail.isEmailRight && binding.inputPassword.inputPassword.isPasswordRight) {
            showLoadingDialog()
            viewModel.login(email, password).observe(this) { result ->
                dismissLoadingDialog()
                when (result) {
                    is Resource.Success -> {
                        movePageAndRemoveTask(MainActivity::class.java)
                    }
                    is Resource.Error -> {
                        binding.loginResult.visibility = View.VISIBLE
                        binding.loginResult.text = String.format(result.message.toString())
                    }
                    is Resource.Loading -> {}
                }
            }
        } else {
            if (!binding.inputEmail.isEmailRight) binding.inputEmail.triggerEmptyField()
            else binding.inputPassword.inputPassword.triggerEmptyField()
        }

    }

    private fun registerListener() {
        binding.btnCreateNewAccount.setOnClickListener {
            movePage(RegisterActivity::class.java)
        }
    }

}