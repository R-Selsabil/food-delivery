package com.example.restaurants.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.restaurants.data.models.Cart
import com.example.restaurants.data.models.CartModel
import com.example.restaurants.data.models.Menu
import com.example.restaurants.data.models.daoModels.CartItem
import com.example.restaurants.data.models.menID
import com.example.restaurants.data.repositories.dataStorage.AppDatabase
import com.example.restaurants.data.repositories.dataStorage.CartDao
import com.example.restaurants.retrofit.MenuEndPoint
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.Response

class CartViewModel : ViewModel() {
    val quantityLiveData: MutableLiveData<Int> = MutableLiveData()
    lateinit var cartDao: CartDao
    var cart = Cart(0, 0, 0, 0, "", "", listOf())

    var cartItems: MutableLiveData<List<CartModel>> = MutableLiveData()
    val errorMessage = MutableLiveData<String>()
    var price = 600
    fun initViewModel(context: Context) {
        cartDao = AppDatabase.buildDatabase(context)?.getCartDao()!!
    }

    fun addMenuToCart(menu: Menu, idRestaurant: Int, quantity: Int) {
        if (verifySameRestaurant(idRestaurant)) {
            //if (verifyCartExist(menu.id)) {
            //    updateQttCart(menu.id, quantity)
            } else {
                val cartItem = CartItem(
                    menu.id,
                    quantity,
                    menu.name,
                    menu.picture,
                    menu.price.toString(),
                    menu.category,
                    idRestaurant
                )
                cartDao.addMenu(cartItem)
            //}
        //} else {
            //println("Not a cart of the current restaurant")
        }
    }

    private fun verifySameRestaurant(idRestaurant: Int): Boolean {
        val restaurantOrder = cartDao.getRestaurantOrder(idRestaurant)
        return restaurantOrder.isNotEmpty()
    }

    private fun verifyCartExist(itemId: Int): Boolean {
        val cartContent = cartDao.getCartContent()
        return cartContent.any { it.cartItemId == itemId }
    }

    private fun updateQttCart(itemId: Int, quantity: Int) {
        val cartItem = cartDao.getCartItemById(itemId)
        val updatedQuantity = cartItem.quantity + quantity
        cartItem.quantity = updatedQuantity
        cartDao.updateMenu(cartItem)
    }

    fun getAllMenusInCart() {
        cartItems.value = emptyList()
        val cartList = cartDao.getCartContent().map { cartItem ->
            // Map each CartItem to CartModel
            CartModel(
                cartItem.cartItemId,
                cartItem.quantity,
                cartItem.name,
                cartItem.logo,
                cartItem.type,
                cartItem.price,
                cartItem.restaurantId
            )
        }
        cartItems.postValue(cartList)
    }

    fun updateCart(cart: CartItem) {
        cartDao.updateMenu(cart)
    }

    fun deleteMenuFromCart(cart: CartItem) {
        cartDao.deleteMenu(cart)
    }

    fun emptyCart() {
        cartItems.value = emptyList()
        cartDao.emptyCart()
    }

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch {
            errorMessage.value = "Une erreur s'est produite"
        }
    }

    /*fun PushCart() {
        val menuEndPoint = MenuEndPoint.createEndpoint()
        cartModelsToCart()
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = menuEndPoint.sendCart(cart)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    // Cart created successfully, handle the response
                    val newCart = response.body()
                    // Do something with the new cart object
                } else {
                    // Error occurred, handle the error
                    val errorMessage = response.message()
                    // Handle the error message appropriately
                }
            }
        }
    }*/
    fun PushCart() {
        val menuEndPoint = MenuEndPoint.createEndpoint()
        val order = Cart(
            price = 3000,
            shipping = 200,
            idUser = 1,
            idRestaurant = 1,
            notes = "Don't forget to add tomato",
            address = "Esi",
            menus = listOf(menID(id = 1))
        )
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = menuEndPoint.sendCart(cart)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    // Cart created successfully, handle the response
                    val newCart = response.body()
                    // Do something with the new cart object
                } else {
                    // Error occurred, handle the error
                    val errorMessage = response.message()
                    // Handle the error message appropriately
                }
            }
        }
        fun cartModelsToCart() {
            cart.address = "Alger"
            cart.price = price
            cart.idRestaurant = cartItems.value?.get(0)!!.restaurantId
            cart.idUser = 1
            cart.notes = "notes"
            cart.shipping = price
            //cart.time = "2023-06-23T00:00:00.000Z"
            cart.menus = listOf(menID(id = 6))
        }
    }
}
