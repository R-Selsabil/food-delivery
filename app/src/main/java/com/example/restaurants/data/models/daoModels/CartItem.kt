package com.example.restaurants.data.models.daoModels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Carts")
data class CartItem(
    @PrimaryKey
    val cartItemId: Int,
    var quantity: Int,
    val name: String,
    val logo: String,
    val type: String,
    val price: String,
    val restaurantId: Int
)
