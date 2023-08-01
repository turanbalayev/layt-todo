package com.turanbalayev.layttodo.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.turanbalayev.layttodo.R
import com.turanbalayev.layttodo.data.models.Priority
import com.turanbalayev.layttodo.data.models.TodoData
import com.turanbalayev.layttodo.databinding.RowLayoutBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    inner class ListViewHolder(val binding: RowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todoData: TodoData) {
            binding.titleTxt.text = todoData.title
            binding.descriptionTxt.text = todoData.description

            val priority = todoData.priority
            when (priority) {
                Priority.HIGH -> binding.priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.red
                    )
                )
                Priority.MEDIUM -> binding.priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.yellow
                    )
                )
                Priority.LOW -> binding.priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.green
                    )
                )
                else -> {
                    binding.priorityIndicator.setCardBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.green
                        )
                    )
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<TodoData>() {
        override fun areItemsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layout = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(layout)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val todoData = differ.currentList[position]
        holder.bind(todoData)
    }


}