package com.base.basehq.data.db.category

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: ProductCategory)

    @Delete(entity = ProductCategory::class)
    fun delete(category: ProductCategory)

    @Query("SELECT * FROM category_table")
    fun getProductCategories(): Flow<List<ProductCategory>>

}