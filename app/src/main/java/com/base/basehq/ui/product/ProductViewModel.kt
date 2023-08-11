package com.base.basehq.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.basehq.data.db.cart.CartDatabase
import com.base.basehq.data.db.cart.CartProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductViewModel(private val database: CartDatabase) : ViewModel() {

    fun insertCartProduct(cartProduct: CartProduct) {
        val dao = database.cartDao
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(cartProduct)
        }
    }

    fun getCartProducts(): Flow<List<CartProduct>> {
        val dao = database.cartDao
        return dao.getProductsFromCart()
    }

}