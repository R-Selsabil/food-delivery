package com.example.restaurants.retrofit

import com.example.restaurants.data.models.Cart
import com.example.restaurants.data.models.CartModel
import com.example.restaurants.data.models.Menu
import com.example.restaurants.data.models.Restaurant
import com.example.restaurants.utils.url
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MenuEndPoint {
    @GET("/api/menus/{idRestaurant}")
    suspend fun getMenuByRestaurantId(
        @Path("idRestaurant") idRestaurant: Int,
    ): Response<List<Menu>>

    @POST("api/carts/sendCart")
    suspend fun sendCart(
        @Body cart: Cart
    ): Response<Cart>

    companion object {
        @Volatile
        var endpoint: MenuEndPoint? = null
        fun createEndpoint(): MenuEndPoint {
            if (endpoint == null) {
                synchronized(this) {
                    endpoint = Retrofit.Builder().baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(MenuEndPoint::class.java)
                }
            }
            return endpoint!!
        }
    }
}