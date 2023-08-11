package com.base.basehq.ui.cart

import androidx.lifecycle.ViewModel
import com.base.basehq.data.db.cart.CartDatabase
import com.base.basehq.data.db.cart.CartProduct
import kotlinx.coroutines.flow.Flow

class CartViewModel(private val database: CartDatabase) : ViewModel() {

    fun getCartProducts(): Flow<List<CartProduct>> {
        val dao = database.cartDao
        return dao.getProductsFromCart()
    }

}