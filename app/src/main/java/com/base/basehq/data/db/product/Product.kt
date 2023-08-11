package com.base.basehq.data.db.product

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.base.basehq.data.db.cart.CartProduct
import com.base.basehq.domain.models.Rating

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey
    var id: Int,
    @ColumnInfo
    var title: String?,
    @ColumnInfo
    var price: Double?,
    @ColumnInfo
    var description: String?,
    @ColumnInfo
    var category: String?,
    @ColumnInfo
    var image: String?,
    @Embedded
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
