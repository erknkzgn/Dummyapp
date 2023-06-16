package com.example.e_commerce_dummy.DataOperations.Room.ss.DAO

import androidx.room.*
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Entity.ShoppingBasket

@Dao
interface ShoppingBasketDAO {
 @Query("SELECT * FROM my_basket")
fun getAll(): List<ShoppingBasket>

@Query("SELECT * FROM my_basket WHERE id IN (:product_shopping_basketIds)")
fun loadAllByIds(product_shopping_basketIds: IntArray): List<ShoppingBasket>

@Query("SELECT * FROM my_basket WHERE brand LIKE :brand AND " +
        "title LIKE :title LIMIT 1")
fun findByBrand(brand: String, title: String): ShoppingBasket

@Insert
fun insertAll(vararg product_shopping_basket: ShoppingBasket)

@Delete
fun delete(product_shopping_basket: ShoppingBasket)
}