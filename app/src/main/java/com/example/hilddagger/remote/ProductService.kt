package com.example.hilddagger.remote

import com.example.hilddagger.connst.Constant.List_Of_Product
import com.example.hilddagger.data.product.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductService{
    @GET(List_Of_Product)
    suspend fun getProduct(): Response<List<Products>>
}
