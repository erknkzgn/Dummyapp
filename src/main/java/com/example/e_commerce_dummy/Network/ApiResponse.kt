package com.example.e_commerce_dummy.Network

import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Model.Product

data class ApiResponseProduct(
    val products: ArrayList<Product> = arrayListOf(),
    val total: Int,
    val skip: Int,
    val limit: Int
    //üstteki veya alttaki gibi data class'ımızı tanımlayabiliriz alttaki kullanım null safety ve
    // eğer değişken ve kolon adları farklı ise ColumnInfo ile kolon adımızı tanımlayabiliriz.
    /*@SerializedName("products") val products: ArrayList<Product> = arrayListOf(),
    @SerializedName("total") val total: Int? = null,
    @SerializedName("skip") val skip: Int? = null,
    @SerializedName("limit") val limit: Int? = null*/
) : NetworkResponse

