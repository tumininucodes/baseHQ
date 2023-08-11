package com.base.basehq.domain.interfaces

import com.base.basehq.domain.models.Product


interface OnProductClickListener {
    fun onItemClick(item: Product, position: Int)
}