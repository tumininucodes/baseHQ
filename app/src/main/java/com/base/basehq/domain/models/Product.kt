package com.base.basehq.domain.models

import com.base.basehq.data.db.cart.CartProduct

data class Product(
    var id: Int,
    var title: String?,
    var price: Double?,
    var description: String?,
    var category: String?,
    var image: String?,
    var rating: Rating?,
) : java.io.Serializable {

    fun toCartProduct(): CartProduct {
        return CartProduct(
            id,
            title.toString(),
            price ?: 0.0,
            description.toString(),
            category.toString(),
            image.toString(),
            rating?.rate.toString()
        )
    }
}
