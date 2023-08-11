package com.base.basehq.data.db.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class CartProduct(
    @PrimaryKey
    var id: Int,
    @ColumnInfo
    var title: String,
    @ColumnInfo
    var price: Double,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var category: String,
    @ColumnInfo
    var image: String,
    @ColumnInfo
    var rating: String,
)