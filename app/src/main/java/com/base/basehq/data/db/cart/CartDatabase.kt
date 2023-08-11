package com.base.basehq.data.db.cart

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class CartDatabase : RoomDatabase() {
    abstract val cartDao: CartDao

    companion object {
        @Volatile
        private var INSTANCE: CartDatabase? = null

        fun getInstance(context: Context): CartDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        CartDatabase::class.java,
                        "cart_table"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}