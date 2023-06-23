package com.example.restaurants.retrofit

import com.example.restaurants.data.models.Restaurant
import com.example.restaurants.utils.url
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RestaurantEndPoint {
    @GET("/api/restaurants")
    suspend fun getAllRestaurants(): Response<List<Restaurant>>

    companion object {
        @Volatile
        var endpoint: RestaurantEndPoint? = null
        fun createEndpoint(): RestaurantEndPoint {
            if (endpoint == null) {
                synchronized(this) {
                    endpoint = Retrofit.Builder().baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(RestaurantEndPoint::class.java)
                }
            }
            return endpoint!!
        }
    }
}
