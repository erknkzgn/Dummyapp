package com.example.e_commerce_dummy.Network

import com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Model.Product
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    @GET("products")
    fun ProductListGetir(): Call<ApiResponseProduct>
    @GET("products/categories")
    fun GetCategories(): Call<List<String>>
}