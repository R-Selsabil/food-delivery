package com.example.restaurants.data.models

data class CartModel(
    val cartItemId: Int,
    var quantity: Int,
    val name: String,
    val logo: String,
    val type: String,
    val price: String,
    val restaurantId: Int
)
