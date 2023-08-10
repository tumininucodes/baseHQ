package com.base.basehq.ui

import androidx.lifecycle.ViewModel
import com.base.basehq.domain.models.Product
import com.base.basehq.domain.repositories.ProductRepository
import com.base.basehq.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    fun getAllCategories(): MutableStateFlow<NetworkResult<List<String>>> {
        return productRepository.getAllCategories()
    }

    fun getProductsInCategory(): MutableStateFlow<NetworkResult<List<Product>>> {
        return productRepository.getProductsInCategory()
    }

}