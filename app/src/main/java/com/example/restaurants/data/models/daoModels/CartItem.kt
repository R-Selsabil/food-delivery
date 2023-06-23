package com.example.restaurants.data.models.daoModels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Carts")
data class CartItem(
    @PrimaryKey
    val cartItemId: String,
    val menuID : String,
    var quantity: Int,
    val name: String,
    val logo: Int,
    val type: String,
    val price: String,
    val restaurantId: String
)
