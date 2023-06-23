package com.example.restaurants.viewmodels

import android.content.Context
import com.example.restaurants.data.models.Menu

import com.example.restaurants.data.models.daoModels.CartItem
import com.example.restaurants.data.repositories.dataStorage.AppDatabase
import com.example.restaurants.data.repositories.dataStorage.CartDao
import kotlin.math.log

class CartViewModel(context: Context) {
    private val cartDao: CartDao = AppDatabase.buildDatabase(context)?.getCartDao()!!
    private val cartItems = mutableListOf<CartItem>()

    /*
    fun addMenuToCart(menu: Menu, idRestaurant:Int, quantity:Int) {
        if(verifySameRestaurant(idRestaurant)){
            if(verifyCartExist(menu.id)){
                updateQttCart(menu.id,quantity,true)
            }
            else{
                val cartItem = CartItem(menu, quantity)
                cartDao.addMenu(cartItem)

            }
        }
        else{
            println("not a cart of the actuel restaurant")
        }
    }

    private fun verifySameRestaurant(idRestaurant: Int): Boolean {
        val restaurantOrder = cartDao.getRestaurantOrder(idRestaurant.toString())
        return restaurantOrder.isNotEmpty()
    }

    private fun verifyCartExist(itemId: Int): Boolean {
        val cartContent = cartDao.getCartContent()
        return cartContent.any { it.cartItemId == itemId }
    }

    private fun updateQttCart(itemId: Int, quantity: Int, increase: Boolean) {
        val cartItem = cartDao.getCartItemById(itemId)
        if (cartItem != null) {
            val updatedQuantity = if (increase) cartItem.quantity + quantity else cartItem.quantity - quantity
            if (updatedQuantity >= 0) {
                cartItem.quantity = updatedQuantity
                cartDao.updateMenu(cartItem)
            }
        }
    }

    fun getAllMenusInCart(): List<CartItem> {
        return cartDao.getCartContent()
    }

    fun updateCart(cart: CartItem) {
        cartDao.updateMenu(cart)
    }

    fun deleteMenuFromCart(cart: CartItem) {
        cartDao.deleteMenu(cart)
    }

    fun emptyCart() {
        cartDao.emptyCart()
    }*/
}
