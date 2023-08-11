package com.base.basehq.data.db.cart

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cartProduct: CartProduct)

    @Delete(entity = CartProduct::class)
    fun delete(cartProduct: CartProduct)

    @Query("SELECT * FROM cart_table")
    fun getProductsFromCart(): Flow<List<CartProduct>>

}