package com.rilodev.d2dapps.pages.auth.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.rilodev.d2dapps.R
import com.rilodev.d2dapps.core.utils.Utils.setShowHidePassword
import com.rilodev.d2dapps.databinding.ActivityRegisterBinding
import com.rilodev.d2dapps.core.data.Resource
import com.rilodev.d2dapps.core.ui.baseview.BaseViewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseViewActivity() {
    private val viewModel by viewModels<RegisterViewModel>()

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!
    private var isPasswordLoginHide = true
    private var isConfirmPasswordLoginHide = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.inputConfirmPassword.inputPassword.hint = String.format("Enter your confirm Password")
        showHidePasswordSetup()
        showHideConfirmPasswordSetup()

        buttonRegisterListener()
        binding.btnIHaveAnAccount.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
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

    private fun showHideConfirmPasswordSetup() {
        with(binding.inputConfirmPassword) {
            btnShowHide.setOnClickListener {
                isConfirmPasswordLoginHide = setShowHidePassword(
                    inputPassword,
                    isConfirmPasswordLoginHide,
                    btnShowHide
                )
            }

        }
    }

    private fun buttonRegisterListener() {
        binding.btnRegister.setOnClickListener {
            val name = binding.inputName
            val email = binding.inputEmail
            val password = binding.inputPassword.inputPassword
            val passwordConfirm = binding.inputConfirmPassword.inputPassword

            if (name.value.isEmpty()) {
                name.triggerEmptyField()
            } else if(!email.isEmailRight) {
                email.triggerEmptyField()
            } else if(!password.isPasswordRight) {
                password.triggerEmptyField()
            } else if(!passwordConfirm.isPasswordRight) {
                passwordConfirm.triggerEmptyField()
            }
            else if (email.isEmailRight && password.isPasswordRight && passwordConfirm.isPasswordRight) {
                if(password.value != passwordConfirm.value) {
                    passwordConfirm.error = getString(R.string.password_and_confirm_not_matches)
                } else {
                    showLoadingDialog()
                    viewModel.register(email.value, name.value, password.value).observe(this) { result ->
                        dismissLoadingDialog()
                        when(result) {
                            is Resource.Success -> {
                                viewModel.saveUser(result.data.toString(), email.value, name.value).observe(this) { resultSave ->
                                    when(resultSave) {
                                        is Resource.Success -> {
                                            Toast.makeText(this, "Registration Success", Toast.LENGTH_SHORT).show()
                                            onBackPressedDispatcher.onBackPressed()
                                        }
                                        is Resource.Error -> {
                                            Toast.makeText(this, resultSave.message.toString(), Toast.LENGTH_SHORT).show()
                                        }
                                        is Resource.Loading -> {}
                                    }
                                    viewModel.reset()
                                }
                            }
                            is Resource.Error -> {
                                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                            }
                            is Resource.Loading -> {}
                        }
                        viewModel.reset()
                    }
                }
            }
        }
    }
}