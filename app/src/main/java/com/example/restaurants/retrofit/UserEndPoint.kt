package com.example.restaurants.retrofit

import com.example.restaurants.data.models.User
import com.example.restaurants.utils.url
import okhttp3.MultipartBody
import okhttp3.RequestBody

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UserEndPoint {
    // Existing endpoints
    @GET("/api/users")
    suspend fun getAllRestaurants(): Response<List<User>>

    @GET("/user/{email}")
    suspend fun getUserByEmail(@Path("email") email: String): Response<User>

    @Multipart
    @POST("/user/createUser")
    suspend fun createUser(
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("password") password: RequestBody,
        @Part picture: MultipartBody.Part?
    ): Response<User>

    @PUT("/user/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body user: User
    ): Response<User>

    @DELETE("/user/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>

    // New endpoint for getting user by email and password
    @GET("/user/{email}/{password}")
    suspend fun getUserByEmailAndPassword(
        @Path("email") email: String,
        @Path("password") password: String
    ): Response<User>

    companion object {
        @Volatile
        var endpoint: UserEndPoint? = null

        fun createEndpoint(): UserEndPoint {
            if (endpoint == null) {
                synchronized(this) {
                    endpoint = Retrofit.Builder().baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(UserEndPoint::class.java)
                }
            }
            return endpoint!!
        }
    }
}
