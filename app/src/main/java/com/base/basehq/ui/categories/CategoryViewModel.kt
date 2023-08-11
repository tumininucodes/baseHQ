package com.base.basehq.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.basehq.data.db.product.Product
import com.base.basehq.data.db.product.ProductDatabase
import com.base.basehq.domain.repositories.ProductRepository
import com.base.basehq.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(private val database: ProductDatabase) : ViewModel() {

    private val productRepository = ProductRepository()

    fun getProductsInCategory(category: String): MutableStateFlow<NetworkResult<List<Product>>> {
        val dao = database.productDao
        val result = MutableStateFlow<NetworkResult<List<Product>>>(NetworkResult.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            dao.getProducts().collect { products ->
                val filteredProducts = products.filter { it.category == category }
                if (filteredProducts.isNotEmpty()) {
                    result.emit(NetworkResult.Success(filteredProducts))
                    getProductsInCategoryFromNetwork(category)
                    cancel()
                } else {
                    productRepository.getProductsInCategory(category).collect {
                        when (it) {
                            is NetworkResult.Loading -> {
                                result.emit(it)
                            }
                            is NetworkResult.Success -> {
                                result.emit(it)
                                for (data in it.data) {
                                    database.productDao.insert(data)
                                }
                            }
                            is NetworkResult.Error -> {
                                result.emit(it)
                            }
                        }
                    }
                }
            }
        }

        return result
    }

    private fun getProductsInCategoryFromNetwork(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.getProductsInCategory(category).collect { resultsState ->
                when (resultsState) {
                    is NetworkResult.Loading -> {
                    }
                    is NetworkResult.Success -> {
                        for (data in resultsState.data) {
                            database.productDao.insert(data)
                        }
                    }
                    is NetworkResult.Error -> {
                    }
                }
            }
        }
    }


}