package com.base.basehq.data.db.cart

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cartProduct: CartProduct)

//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun update(user: CurrentUser)

    @Delete(entity = CartProduct::class)
    fun delete(cartProduct: CartProduct)

    @Query("SELECT * FROM cart_table")
    fun getProductsFromCart(): Flow<List<CartProduct>>

}