package com.rilodev.d2dapps.pages.main.presence

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rilodev.d2dapps.core.data.local.entity.PresenceEntity
import com.rilodev.d2dapps.core.utils.Constants
import com.rilodev.d2dapps.core.utils.Utils
import com.rilodev.d2dapps.databinding.FragmentPresenceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PresenceFragment : Fragment() {
    private val viewModel by viewModels<PresenceViewModel>()
    private var _binding: FragmentPresenceBinding? = null
    private val binding get() = _binding!!
    private var presenceDone = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPresenceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.presenceDate.text = Utils.perfectDate()
        loadData()
    }

    private fun loadData() {
        val currentDate =
            Utils.dateFormatter(Utils.getCurrentDateTime(), Constants.DATETIME_FORMATTER_DD_MM_YYYY)

        viewModel.isPresenceExist(currentDate).observe(viewLifecycleOwner) { result ->
            presenceDone = result
            if (result) {
                binding.infoDailyPresence.text = String.format("You've been do presence today")
                binding.presenceButton.text = String.format("Done for Today")
            } else {
                binding.presenceButton.setOnClickListener {
                    doPresence()
                }
            }
        }
    }

    private fun doPresence() {
        val currentDateTime = Utils.getCurrentDateTime()
        val presence = PresenceEntity(
            null,
            Utils.dateFormatter(currentDateTime, Constants.DATETIME_FORMATTER_DD_MM_YYYY),
            Utils.timeFormatter(currentDateTime, Constants.DATETIME_FORMATTER_DD_MM_YYYY)
        )
        viewModel.insertPresence(presence)
        binding.infoDailyPresence.text = String.format("You've been do presence today")
        binding.presenceButton.text = String.format("Done for Today")
    }
}