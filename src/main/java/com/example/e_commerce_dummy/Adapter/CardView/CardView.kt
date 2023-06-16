package com.example.e_commerce_dummy.Adapter.CardView

import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Model.Product


class CardView(product: Product) {

    val title: String
    val description: String
    val price: String
    val images: ArrayList<String>

    init {
        this.title = product.title
        this.description = product.description
        this.price = product.price
        this.images = product.images

    }

}