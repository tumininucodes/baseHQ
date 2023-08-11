package com.base.basehq.ui

import androidx.lifecycle.ViewModel
import com.base.basehq.domain.models.Product
import com.base.basehq.domain.repositories.ProductRepository
import com.base.basehq.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow

class CategoryViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    fun getProductsInCategory(category: String): MutableStateFlow<NetworkResult<List<Product>>> {
        return productRepository.getProductsInCategory(category)
    }

}