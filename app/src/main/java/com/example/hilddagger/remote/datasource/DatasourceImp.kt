package com.example.hilddagger.remote.datasource

import com.example.hilddagger.data.product.Products
import com.example.hilddagger.remote.ProductService
import retrofit2.Response
import javax.inject.Inject
class DatasourceImp @Inject constructor(
    private val userService: ProductService
) : IDatasource {

    override suspend fun getProduct(): Response<List<Products>> {
        return userService.getProduct()
    }
}
