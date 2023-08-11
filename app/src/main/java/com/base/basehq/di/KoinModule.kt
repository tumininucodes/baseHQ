package com.base.basehq.di

import com.base.basehq.data.db.cart.CartDatabase
import com.base.basehq.data.db.category.CategoryDatabase
import com.base.basehq.data.db.product.ProductDatabase
import com.base.basehq.ui.categories.CategoryViewModel
import com.base.basehq.ui.home.HomeViewModel
import com.base.basehq.ui.product.ProductViewModel
import com.base.basehq.ui.cart.CartViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { CartDatabase.getInstance(androidContext()) }
    single { CategoryDatabase.getInstance(androidContext()) }
    single { ProductDatabase.getInstance(androidContext()) }
    viewModel { CartViewModel(get()) }
    viewModel { ProductViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { CategoryViewModel(get()) }
}