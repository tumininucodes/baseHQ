package com.base.basehq.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.inflate
import com.base.basehq.R
import com.base.basehq.databinding.ChildCategoryBinding
import com.base.basehq.utils.capitalise
import kotlinx.coroutines.flow.combine

class CategoryAdapter : ListAdapter<String, CategoryAdapter.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String,
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String,
            ): Boolean =
                oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChildCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvCategory.text = getItem(position).capitalise()
    }

    inner class ViewHolder(val binding: ChildCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)


}