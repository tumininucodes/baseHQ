package com.base.basehq.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.base.basehq.R
import com.base.basehq.data.db.cart.CartProduct
import com.base.basehq.databinding.ChildCartBinding
import com.base.basehq.domain.interfaces.OnCartProductClickListener
import com.base.basehq.utils.capitalise
import com.bumptech.glide.Glide

class CartAdapter(val context: Context, private val listener: OnCartProductClickListener) :
    ListAdapter<CartProduct, CartAdapter.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CartProduct>() {
            override fun areItemsTheSame(
                oldItem: CartProduct,
                newItem: CartProduct,
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CartProduct,
                newItem: CartProduct,
            ): Boolean =
                oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChildCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartProduct = getItem(position)
        with(holder) {
            Glide.with(context).load(cartProduct.image).into(binding.ivProductImage)
            binding.tvProductTitle.text = cartProduct.title
            val price = context.getString(R.string.price, cartProduct.price)
            binding.tvProductCost.text =
                price.substring(startIndex = 0, endIndex = price.indexOf(".").plus(3))
            binding.tvProductCategory.text = cartProduct.category.capitalise()
        }
    }

    inner class ViewHolder(val binding: ChildCartBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.btnRemove.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            val item = getItem(position)
            listener.onItemClick(item, position)
        }

    }


}