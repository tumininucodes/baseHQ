package com.base.basehq.domain.repositories

import com.base.basehq.data.network.ApiClient
import com.base.basehq.domain.models.Product
import com.base.basehq.utils.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductRepository {

    fun getAllCategoriesFromNetwork(): MutableStateFlow<NetworkResult<List<String>>> {

        val categoriesState = MutableStateFlow<NetworkResult<List<String>>>(NetworkResult.Loading)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.retrofitService.getAllCategories()
                if (response.isSuccessful && response.body() != null) {
                    categoriesState.emit(NetworkResult.Success(response.body()!!))

                } else {
                    categoriesState.emit(NetworkResult.Error(Throwable("Error fetching categories")))
                }
            } catch (e: Exception) {
                categoriesState.emit(NetworkResult.Error(e))
            }
        }

        return categoriesState
    }


    fun getProductsInCategory(category: String): MutableStateFlow<NetworkResult<List<Product>>> {

        val productsState = MutableStateFlow<NetworkResult<List<Product>>>(NetworkResult.Loading)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.retrofitService.getProductInCategory(category)
                if (response.isSuccessful && response.body() != null) {
                    productsState.emit(NetworkResult.Success(response.body()!!))
                } else {
                    productsState.emit(NetworkResult.Error(Throwable("Error fetching products")))
                }
            } catch (e: Exception) {
                productsState.emit(NetworkResult.Error(e))
            }
        }

        return productsState
    }
}