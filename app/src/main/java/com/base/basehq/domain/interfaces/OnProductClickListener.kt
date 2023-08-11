package com.base.basehq.domain.interfaces

import com.base.basehq.data.db.product.Product


interface OnProductClickListener {
    fun onItemClick(item: Product, position: Int)
}