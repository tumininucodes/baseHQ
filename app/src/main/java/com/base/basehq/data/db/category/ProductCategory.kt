package com.base.basehq.data.db.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class ProductCategory(
    @PrimaryKey
    var id: Int,
    @ColumnInfo
    var title: String,
)
