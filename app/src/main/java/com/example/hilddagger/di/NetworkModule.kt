package com.example.hilddagger.di

import com.example.hilddagger.connst.Constant
import com.example.hilddagger.remote.ProductService
import com.example.hilddagger.remote.datasource.DatasourceImp
import com.example.hilddagger.remote.datasource.IDatasource
import com.example.hilddagger.remote.repository.RepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL) // For URL
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ProductService{
        return retrofit.create(ProductService::class.java) // For Service
    }

    @Singleton
    @Provides
    fun provideDataSource(productService: ProductService): IDatasource{
        return DatasourceImp(productService) // For Data Source
    }


    @Singleton
    @Provides
    fun provideLoginRepo(dataSource: IDatasource): RepositoryImp {
        return RepositoryImp(dataSource)
    }

}