package com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.e_commerce_dummy.DataOperations.Room.Model.IProduct
import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Entity.Favorite

@Entity
class Product(
    id: Int,
    title: String,
    description: String,
    price: String,
    discountPercentage: String,
    rating: String,
    stock: String,
    brand: String,
    category: String,
    thumbnail: String,
    imageList: ArrayList<String>
) : IProduct {

    var id: Int
    val title: String
    val description: String
    val price: String
    val discountPercentage: String
    val rating: String
    val stock: String
    val brand: String
    val category: String
    val thumbnail: String
    val images: ArrayList<String>


    init {
        this.id = id
        this.title = title
        this.description = description
        this.price = price
        this.discountPercentage = discountPercentage
        this.rating = rating
        this.stock = stock
        this.brand = brand
        this.category = category
        this.thumbnail = thumbnail
        this.images = imageList

    }
    fun convertToFavorite(item: Product): Favorite {
        return Favorite(item.id,item.title,item.description,item.price,item.discountPercentage,item.rating,item.stock,item.brand,item.category,item.thumbnail,item.images)
    }
}