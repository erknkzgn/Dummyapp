package com.example.e_commerce_dummy.DataOperations.Room

import android.content.Context
import androidx.room.*
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.FavoriteDAO
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.ShoppingBasketDAO
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Entity.Converter
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Entity.Favorite
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Entity.ShoppingBasket



@Database(entities = [Favorite::class, ShoppingBasket::class], version = 3)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDAO
    abstract fun shoppinBasketDao(): ShoppingBasketDAO

    companion object {
        private var instance: AppDatabase? = null

        fun getFavoriteDatabase(context: Context): AppDatabase? {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "favorite.db"
                ).addTypeConverter(Converter()).allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}