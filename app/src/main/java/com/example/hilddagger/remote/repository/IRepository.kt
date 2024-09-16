package com.example.hilddagger.remote.repository

import com.example.hilddagger.data.product.Products
import retrofit2.Response

interface IRepository {
    suspend fun getProduct() : Response<List<Products>>
}