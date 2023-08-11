package com.base.basehq.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.basehq.data.db.cart.CartDatabase
import com.base.basehq.data.db.cart.CartProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CartViewModel(private val database: CartDatabase) : ViewModel() {

    fun getCartProducts(): Flow<List<CartProduct>> {
        val dao = database.cartDao
        return dao.getProductsFromCart()
    }

    fun removeCartProduct(cartProduct: CartProduct) {
        val dao = database.cartDao
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(cartProduct)
        }
    }

}