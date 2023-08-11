package com.base.basehq.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.basehq.data.db.category.CategoryDatabase
import com.base.basehq.data.db.category.ProductCategory
import com.base.basehq.domain.repositories.ProductRepository
import com.base.basehq.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val database: CategoryDatabase) : ViewModel() {

    private val productRepository = ProductRepository()

    fun getAllCategories(): MutableStateFlow<NetworkResult<List<String>>> {
        val dao = database.categoryDao
        val result = MutableStateFlow<NetworkResult<List<String>>>(NetworkResult.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            dao.getProductCategories().collect { categories ->
                if (categories.isNotEmpty()) {
                    result.emit(NetworkResult.Success(categories.map { it.title }))
                    getAllCategoriesFromNetwork()
                    cancel()
                } else {
                    productRepository.getAllCategoriesFromNetwork().collect {
                        when (it) {
                            is NetworkResult.Loading -> {
                                result.emit(it)
                            }
                            is NetworkResult.Success -> {
                                result.emit(it)
                                for (dataString in it.data) {
                                    val data = ProductCategory(id = it.data.indexOf(dataString),
                                        title = dataString)
                                    database.categoryDao.insert(data)
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

    private fun getAllCategoriesFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.getAllCategoriesFromNetwork().collect { resultsState ->
                when (resultsState) {
                    is NetworkResult.Loading -> {
                    }
                    is NetworkResult.Success -> {
                        for (dataString in resultsState.data) {
                            val data = ProductCategory(id = resultsState.data.indexOf(dataString),
                                title = dataString)
                            database.categoryDao.insert(data)
                        }

                    }
                    is NetworkResult.Error -> {
                    }
                }
            }
        }
    }

}