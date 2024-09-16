package com.example.hilddagger.ui.theme.screen


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilddagger.data.product.Products
import com.example.hilddagger.remote.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class ProductVm @Inject constructor(
    private val productRepo: IRepository,
    @ApplicationContext private val context: Context // Inject the application context
) : ViewModel() {

    private val _product = MutableLiveData<List<Products>>() // Changed type to List<Products>
    val products: LiveData<List<Products>> get() = _product

    private val networkChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // Implement network change handling logic here
            fetchProduct()
        }
    }

    init {
        // Register the network change receiver in the init block
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkChangeReceiver, intentFilter)

        // Fetch the initial list of products
        fetchProduct()
    }

    fun fetchProduct() {
        viewModelScope.launch {
            try {
                // Fetch the product list from the repository
                val response = productRepo.getProduct()

                // Extract the list of products from the response body
                val productList = response.body() ?: emptyList()

                // Log the title of the first product (if available)
                val firstProductTitle = productList.getOrNull(0)?.title
                Log.d(">>", "Response: $firstProductTitle")

                // Post the product list to the LiveData
                _product.postValue(productList)
            } catch (e: Exception) {
                Log.e(">>", "Error fetching products: ${e.message}")
                // Handle the error (e.g., post an empty list or show an error state)
                _product.postValue(emptyList()) // In case of error, post an empty list
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Unregister the network change receiver when ViewModel is cleared
        context.unregisterReceiver(networkChangeReceiver)
    }
}
