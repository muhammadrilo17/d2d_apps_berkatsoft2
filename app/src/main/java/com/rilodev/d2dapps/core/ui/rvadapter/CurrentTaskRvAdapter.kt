package com.rilodev.d2dapps.core.ui.rvadapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rilodev.d2dapps.R
import com.rilodev.d2dapps.core.domain.model.TaskModel
import com.rilodev.d2dapps.core.utils.Constants
import com.rilodev.d2dapps.core.utils.Utils
import com.rilodev.d2dapps.databinding.ItemTaskBinding

class CurrentTaskRvAdapter(): RecyclerView.Adapter<CurrentTaskRvAdapter.ViewHolder>() {
    private val listData = ArrayList<TaskModel>()
    var onItemClicked: ((TaskModel, Int) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<TaskModel>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemTaskBinding.bind(view)
        fun bind(task: TaskModel) {
            with(binding) {
                val createdTime = if(task.timeDateCreated.isNotEmpty()) Utils.timeFormatter(task.timeDateCreated, Constants.DATETIME_FORMATTER_DD_MM_YYYY)
                else "0"

                val endTime = if(task.timeDateEnded == null) "Now"
                else Utils.timeFormatter(task.timeDateEnded.toString(), Constants.DATETIME_FORMATTER_DD_MM_YYYY)

                dateOfTask.text = String.format("$createdTime - $endTime")
                description.text = String.format(task.description)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClicked?.invoke(listData[absoluteAdapterPosition], absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}