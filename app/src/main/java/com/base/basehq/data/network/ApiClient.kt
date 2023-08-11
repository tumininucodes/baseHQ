package com.base.basehq.data.network

import com.base.basehq.domain.models.Product
import com.base.basehq.utils.Constants
import com.base.basehq.utils.Constants.moshi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("products/categories")
    suspend fun getAllCategories(): Response<List<String>>


    @GET("products/category/{category}")
    suspend fun getProductInCategory(
        @Path("category") category: String,
    ): Response<List<Product>>

}

object ApiClient {
    val retrofitService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        retrofit.create(ApiService::class.java)
    }
}