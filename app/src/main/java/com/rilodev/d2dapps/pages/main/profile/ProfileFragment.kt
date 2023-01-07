package com.rilodev.d2dapps.pages.main.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rilodev.d2dapps.R
import com.rilodev.d2dapps.core.ui.baseview.BaseViewFragment
import com.rilodev.d2dapps.databinding.FragmentProfileBinding
import com.rilodev.d2dapps.pages.auth.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseViewFragment() {
    private val viewModel by viewModels<ProfileViewModel>()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        buttonExit()
        loadData()
    }

    private fun buttonExit() {
        binding.btnExit.setOnClickListener {
            viewModel.logout()
            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }

    private fun loadData() {
        viewModel.userData.observe(viewLifecycleOwner) { result ->
            binding.email.text = result.email
            binding.name.text = result.name
        }
    }
}