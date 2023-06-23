package com.example.restaurants.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurants.data.models.Restaurant
import com.example.restaurants.data.repositories.dataStorage.AppDatabase
import com.example.restaurants.retrofit.RestaurantEndPoint
import com.example.restaurants.views.main_restaurants_activity.adapters.AdapterRestaurants
import kotlinx.coroutines.*

class RestaurantViewModel : ViewModel(){
    val restaurants = MutableLiveData<List<Restaurant>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()


    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite"
        }
    }

    fun loadRestaurants() {
        /*val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                //progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireActivity(), "Une erreur s'est produite une", Toast.LENGTH_SHORT).show()
            }
        }*/
        //progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = RestaurantEndPoint.createEndpoint().getAllRestaurants()
            withContext(Dispatchers.Main) {
                loading.value = false
                if (response.isSuccessful && response.body() != null) {
                    restaurants.value = response.body()
                } else {
                    errorMessage.value = "Une erreur s'est produite"
                }
            }
        }
    }


}