package com.example.e_commerce_dummy.Adapter.Categories

class Categories(categoryName: String) {

    private var categoryName: String

    init {
        this.categoryName = categoryName
    }

    fun getCategoryName() : String{
        return categoryName
    }
}