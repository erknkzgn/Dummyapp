package com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.e_commerce_dummy.DataOperations.Room.Model.IProduct

@Entity(tableName = "my_basket")
data class ShoppingBasket (
                     @PrimaryKey(autoGenerate = true) val id: Int?,
                     @ColumnInfo(name = "Title") val title: String?,
                     @ColumnInfo(name = "Description") val description: String?,
                     @ColumnInfo(name = "price") val price: String?,
                     @ColumnInfo(name = "discountPercentage") val discountPercentage: String?,
                     @ColumnInfo(name = "rating") val rating: String?,
                     @ColumnInfo(name = "stock") val stock: String?,
                     @ColumnInfo(name = "brand") val brand: String?,
                     @ColumnInfo(name = "category") val category: String?,
                     @ColumnInfo(name = "thumbnail") val thumbnail: String?,
                     @ColumnInfo(name = "images") val images: ArrayList<String?>) : IProduct
