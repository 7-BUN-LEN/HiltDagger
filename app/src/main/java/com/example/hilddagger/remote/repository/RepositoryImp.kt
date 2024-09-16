package com.example.hilddagger.remote.repository

import com.example.hilddagger.data.product.Products
import com.example.hilddagger.remote.datasource.IDatasource
import retrofit2.Response
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val dataSource: IDatasource
) : IRepository {
    override suspend fun getProduct(): Response<List<Products>> {
        return dataSource.getProduct()
    }
}
