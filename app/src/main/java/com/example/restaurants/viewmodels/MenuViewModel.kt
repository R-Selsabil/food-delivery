package com.example.restaurants.viewmodels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurants.data.models.Menu
import com.example.restaurants.data.models.CartModel
import com.example.restaurants.retrofit.MenuEndPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel : ViewModel() {
    var item : Menu? = null
    var qttOfItem = "0"
    var idRestaurant: Int = -1
    //var itemDetail : Menu? = null
    val menus = MutableLiveData<List<Menu>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()


    //the user select an item to desplay it's details from the menu of a restaurant
    fun selectMenuItem(menuItem: Menu) {
        item = menuItem
    }

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite"
        }
    }

    fun loadMenuofRestaurant(idRestaurant : Int) {
        menus.value = emptyList()
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
    }
}






