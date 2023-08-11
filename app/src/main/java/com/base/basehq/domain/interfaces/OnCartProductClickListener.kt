package com.base.basehq.domain.interfaces

import com.base.basehq.data.db.cart.CartProduct


interface OnCartProductClickListener {
    fun onItemClick(item: CartProduct, position: Int)
}