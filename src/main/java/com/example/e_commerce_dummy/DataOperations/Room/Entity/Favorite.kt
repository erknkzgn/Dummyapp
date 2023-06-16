package com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Entity

import androidx.room.*
import com.example.e_commerce_dummy.DataOperations.Room.Model.IProduct
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Model.Product

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "Description") val description: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "discountPercentage") val discountPercentage: String,
    @ColumnInfo(name = "rating") val rating: String,
    @ColumnInfo(name = "stock") val stock: String,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "images") val images: ArrayList<String>
) : IProduct
