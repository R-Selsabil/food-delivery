package com.example.restaurants.viewmodels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurants.data.models.Menu
import com.example.restaurants.data.models.CartItem
import com.example.restaurants.data.models.Restaurant
import com.example.restaurants.retrofit.MenuEndPoint
import com.example.restaurants.retrofit.RestaurantEndPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel : ViewModel() {
    var itemDetail : Menu? = null
    var qttOfItem = "0"
    val menu = mutableListOf<Menu>()
    val cartItems = mutableListOf<CartItem>()

    fun addToCart(menuItem: Menu, quantity: Int) {
        val cartItem = CartItem(menuItem, quantity)
        cartItems.add(cartItem)
    }
    //the user select an item to desplay it's details from the menu of a restaurant
    fun selectMenuItem(menuItem: Menu) {
        itemDetail = menuItem
    }

    fun removeFromCart(cartItem: CartItem) {
        cartItems.remove(cartItem)
    }
}

    /*var itemDetail : Menu? = null
    val menus = MutableLiveData<List<Menu>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun selectMenuItem(menuItem: Menu) {
        itemDetail = menuItem
    }

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite"
        }
    }

    fun loadMenuofRestaurant(idRestaurant : Int) {
        /*val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                //progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireActivity(), "Une erreur s'est produite une", Toast.LENGTH_SHORT).show()
            }
        }*/
        //progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = MenuEndPoint.createEndpoint().getMenuByRestaurantId(idRestaurant)
            withContext(Dispatchers.Main) {
                loading.value = false
                if (response.isSuccessful && response.body() != null) {
                    menus.value = response.body()
                } else {
                    errorMessage.value = "Une erreur s'est produite"
                }
            }
        }
    }*/

