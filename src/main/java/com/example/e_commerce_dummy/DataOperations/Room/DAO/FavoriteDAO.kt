package com.example.e_commerce_dummy.DataOperations.Room.ss.DAO

import androidx.room.*
import com.example.e_commerce_dummy.DataOperations.Room.Model.IProduct
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Entity.Favorite

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM favorites")
    fun getAll(): List<Favorite>

    @Query("SELECT * FROM favorites WHERE id IN (:favoriteIds)")
    fun loadAllByIds(favoriteIds: IntArray): List<Favorite>

    @Query("SELECT * FROM favorites WHERE brand LIKE :first AND " +
            "title LIKE :last LIMIT 1")
    fun findByBrand(first: String, last: String): Favorite

    @Insert
    fun insertAll(vararg favorites: Favorite)

    @Delete
    fun delete(favorite: Favorite)
}