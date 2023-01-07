package com.rilodev.d2dapps.pages.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rilodev.d2dapps.R
import com.rilodev.d2dapps.core.data.local.entity.TaskEntity
import com.rilodev.d2dapps.core.domain.model.TaskModel
import com.rilodev.d2dapps.core.ui.baseview.BaseViewFragment
import com.rilodev.d2dapps.core.ui.rvadapter.CurrentTaskRvAdapter
import com.rilodev.d2dapps.core.utils.Constants
import com.rilodev.d2dapps.core.utils.CustomDialog.taskDetailDialog
import com.rilodev.d2dapps.core.utils.Utils
import com.rilodev.d2dapps.core.utils.Utils.perfectDate
import com.rilodev.d2dapps.core.utils.mapper.TaskMapper
import com.rilodev.d2dapps.core.utils.payload.TaskPayload
import com.rilodev.d2dapps.databinding.FragmentHomeBinding
import com.rilodev.d2dapps.databinding.PopupAddTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseViewFragment() {
    private val viewModel by viewModels<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentTaskAdapter: CurrentTaskRvAdapter
    private var lastTask: TaskModel? = null
    private var lastIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        buttonAddTask()
        loadTasks()

        loadUser()
    }

    private fun loadUser() {
        viewModel.userData.observe(viewLifecycleOwner) { userData ->
            binding.hiUser.text = String.format("Hi, ${userData.name}")
        }
        binding.currentDate.text = perfectDate()
    }

    private fun recyclerViewCurrentTask() {
        currentTaskAdapter = CurrentTaskRvAdapter()

        currentTaskAdapter.onItemClicked = { task, position ->
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
            requireActivity().taskDetailDialog(timeDate, task.description, lastIndex == position) {
                endSelectedTask(task)
            }
        }

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            binding.recyclerView.adapter = currentTaskAdapter
        }
    }

    private fun buttonAddTask() {
        binding.btnAddTask.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext()).create()
            val view = PopupAddTaskBinding.inflate(layoutInflater)

            view.btnSave.setOnClickListener {
                val timeCreated = Utils.getCurrentDateTime()
                val desc = view.inputDescription.text.toString().trim()
                viewModel.addNewTask(TaskPayload(desc, timeCreated, null))
                if(lastTask?.timeDateEnded == null){
                    viewModel.updateTask(
                        TaskEntity(
                            lastTask?.id,
                            lastTask?.description.toString(),
                            lastTask?.timeDateCreated.toString(),
                            timeCreated
                        )
                    )
                }
                Toast.makeText(context, "Task added", Toast.LENGTH_SHORT).show()
                loadTasks()
                builder.dismiss()
            }

            builder.setView(view.root)
            builder.setCancelable(true)
            builder.show()
        }
    }

    private fun loadTasks() {
        recyclerViewCurrentTask()
        binding.progressBar.visibility = View.VISIBLE
        binding.taskLoadingResult.visibility = View.VISIBLE
        binding.taskLoadingResult.text = getString(R.string.loading)
        viewModel.loadTasks().observe(viewLifecycleOwner) { result ->
            binding.progressBar.visibility = View.GONE
            val data = result.map { TaskMapper.entityToDomain(it) } as ArrayList
            lastIndex = data.size - 1
            currentTaskAdapter.setData(data)

            if (data.size < 1) {
                binding.taskLoadingResult.text = String.format("There's no data")
                lastTask = null
            } else {
                lastTask = data[data.size - 1]
                binding.progressBar.visibility = View.GONE
                binding.taskLoadingResult.visibility = View.GONE
            }
        }
    }

    private fun endSelectedTask(taskModel: TaskModel) {
        viewModel.updateTask(
            TaskEntity(
                taskModel.id,
                taskModel.description,
                taskModel.timeDateCreated,
                Utils.getCurrentDateTime(),
            ),
        )
        Toast.makeText(context, "Data Updated!", Toast.LENGTH_SHORT).show()
        loadTasks()
    }
}