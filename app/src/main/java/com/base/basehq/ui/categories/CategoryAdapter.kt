package com.base.basehq.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.base.basehq.R
import com.base.basehq.databinding.ChildCategoryBinding
import com.base.basehq.domain.interfaces.OnCategoryClickListener
import com.base.basehq.utils.capitalise

class CategoryAdapter(private val listener: OnCategoryClickListener) :
    ListAdapter<String, CategoryAdapter.ViewHolder>(COMPARATOR) {

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
        val category = getItem(position)
        holder.binding.tvCategory.text = category.capitalise()
        when (category) {
            "electronics" -> {
                holder.binding.ivImage.setImageResource(R.drawable.gadgets)
            }
            "jewelery" -> {
                holder.binding.ivImage.setImageResource(R.drawable.jewelry)
            }
            "men's clothing" -> {
                holder.binding.ivImage.setImageResource(R.drawable.man)
            }
            "women's clothing" -> {
                holder.binding.ivImage.setImageResource(R.drawable.fashion)
            }
            else -> {
                holder.binding.ivImage.setImageResource(R.drawable.category)
            }
        }
    }

    inner class ViewHolder(val binding: ChildCategoryBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.mainContainer.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            val item = getItem(position)
            listener.onItemClick(item, position)
        }

    }


}