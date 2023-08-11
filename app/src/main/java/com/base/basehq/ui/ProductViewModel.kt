package com.base.basehq.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.basehq.data.db.cart.CartDatabase
import com.base.basehq.data.db.cart.CartProduct
import com.base.basehq.domain.models.Product
import com.base.basehq.domain.repositories.ProductRepository
import com.base.basehq.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val database: CartDatabase) : ViewModel() {

    fun insertCartProduct(cartProduct: CartProduct) {
        val dao = database.cartDao
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(cartProduct)
        }
    }

}