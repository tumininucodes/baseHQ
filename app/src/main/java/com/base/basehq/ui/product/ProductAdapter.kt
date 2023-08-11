package com.base.basehq.ui.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.base.basehq.R
import com.base.basehq.databinding.ChildProductBinding
import com.base.basehq.domain.interfaces.OnProductClickListener
import com.base.basehq.data.db.product.Product
import com.bumptech.glide.Glide

class ProductAdapter(val context: Context, private val listener: OnProductClickListener) :
    ListAdapter<Product, ProductAdapter.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(
                oldItem: Product,
                newItem: Product,
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: Product,
                newItem: Product,
            ): Boolean =
                oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChildProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        with(holder) {
            Glide.with(context).load(product.image).into(binding.ivProductImage)
            binding.tvProductTitle.text = product.title
            val price = context.getString(R.string.price, product.price)
            binding.tvProductPrice.text =
                price.substring(startIndex = 0, endIndex = price.indexOf(".").plus(3))
        }
    }

    inner class ViewHolder(val binding: ChildProductBinding) :
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