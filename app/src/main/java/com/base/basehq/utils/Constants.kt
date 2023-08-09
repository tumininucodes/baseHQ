package com.base.basehq.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object Constants {

    const val BASE_URL: String = " https://fakestoreapi.com/"

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


}