package com.example.restaurants.data.repositories.dataStorage

import androidx.room.*
import com.example.restaurants.data.models.daoModels.CartItem

@Dao
interface CartDao {
    // Dispaly the cart
    @Query("select * from Carts ")
    fun getCartContent(): List<CartItem>

    // Get the restaurant's id of the current order
    @Query("SELECT * FROM Carts WHERE restaurantId = :restaurantId")
    fun getRestaurantOrder(restaurantId: Int): List<CartItem>

    @Query("SELECT restaurantId FROM Carts")
    fun getRestaurantId() : Int

    // Insert a new Menu to the cart
    @Insert
    fun addMenu(cart: CartItem)

    // Update a cart
    @Update
    fun updateMenu(cart: CartItem)

    // Delete a menu from the cart
    @Delete
    fun deleteMenu(cart: CartItem)

    // Empty cart
    @Query("DELETE FROM Carts")
    fun emptyCart()
    @Query("SELECT * FROM Carts WHERE cartItemId = :itemId")
    fun getCartItemById(itemId: Int): CartItem
}