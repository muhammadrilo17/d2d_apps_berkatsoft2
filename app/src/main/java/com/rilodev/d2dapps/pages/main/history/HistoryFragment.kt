package com.rilodev.d2dapps.pages.main.history

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rilodev.d2dapps.core.ui.rvadapter.CurrentTaskRvAdapter
import com.rilodev.d2dapps.core.utils.Constants
import com.rilodev.d2dapps.core.utils.CustomDialog.taskDetailDialog
import com.rilodev.d2dapps.core.utils.Utils
import com.rilodev.d2dapps.core.utils.mapper.TaskMapper
import com.rilodev.d2dapps.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.util.*

@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private val viewModel by viewModels<HistoryViewModel>()
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private var calendarHistory = Calendar.getInstance()
    private lateinit var currentTaskAdapter: CurrentTaskRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {
        dateHistoryListener()
        recyclerViewCurrentTask()
    }


    private fun dateHistoryListener() {
        binding.historyDateButton.setOnClickListener {
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    calendarHistory.set(Calendar.YEAR, year)
                    calendarHistory.set(Calendar.MONTH, monthOfYear)
                    calendarHistory.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    binding.historyDateButton.text = String.format("$dayOfMonth/${monthOfYear+1}/$year")
                    val day = if(dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth
                    val month = if(monthOfYear+1 < 10) "0${monthOfYear+1}" else (monthOfYear+1)
                    loadHistoryByDate("$day-${month}-$year")
                }

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                dateSetListener,
                calendarHistory.get(Calendar.YEAR),
                calendarHistory.get(Calendar.MONTH),
                calendarHistory.get(Calendar.DAY_OF_MONTH)
            )

            val tempCurrentDate = LocalDateTime.now()
            val maxDate = Calendar.getInstance()
            maxDate[tempCurrentDate.year, (tempCurrentDate.monthValue-1)] =
                tempCurrentDate.dayOfMonth

            datePickerDialog.datePicker.maxDate = maxDate.timeInMillis

            datePickerDialog.show()
        }
    }

    private fun loadHistoryByDate(date: String) {
        viewModel.loadTasksByDate(date).observe(viewLifecycleOwner) { result ->
            viewModel.isPresenceExist(date).observe(viewLifecycleOwner) { isExist ->
                if(isExist) binding.presenceDate.text = String.format("You've been do Presence at $date")
                else binding.presenceDate.text = String.format("No Presence at $date")
            }
            val data = result.map { TaskMapper.entityToDomain(it) } as ArrayList
            currentTaskAdapter.setData(data)
            if (data.size < 1) {
                binding.taskLoadingResult.visibility = View.VISIBLE
            } else {
                binding.taskLoadingResult.visibility = View.GONE
            }
        }
    }


    private fun recyclerViewCurrentTask() {
        currentTaskAdapter = CurrentTaskRvAdapter()

        currentTaskAdapter.onItemClicked = { task, _ ->
            val timeEnd = if (task.timeDateEnded == null) "Now" else Utils.timeFormatter(
                task.timeDateEnded!!, Constants.DATETIME_FORMATTER_DD_MM_YYYY,
            )
            val timeDate = String.format(
                "${
                    Utils.timeFormatter(
                        task.timeDateCreated,
                        Constants.DATETIME_FORMATTER_DD_MM_YYYY
                    )
                } - $timeEnd"
            )
            requireActivity().taskDetailDialog(timeDate, task.description)
        }

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            binding.recyclerView.adapter = currentTaskAdapter
        }
    }
}