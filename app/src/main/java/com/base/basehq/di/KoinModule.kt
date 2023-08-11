package com.base.basehq.di

import com.base.basehq.data.db.cart.CartDatabase
import com.base.basehq.ui.ProductViewModel
import com.base.basehq.ui.cart.CartViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { CartDatabase.getInstance(androidContext()) }
    viewModel { CartViewModel(get()) }
    viewModel { ProductViewModel(get()) }
}