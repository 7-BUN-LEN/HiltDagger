package com.example.hilddagger.remote.datasource

import com.example.hilddagger.data.product.Products
import retrofit2.Response

interface IDatasource {
    suspend fun getProduct(): Response<List<Products>>
}