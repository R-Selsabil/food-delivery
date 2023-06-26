package com.example.restaurants.viewmodels

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurants.data.models.User
import com.example.restaurants.data.repositories.sharedPreferences
import com.example.restaurants.retrofit.UserEndPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody


class UserViewModel : ViewModel() {

    private lateinit var database: sharedPreferences
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun initViewModel(context:Activity) {
        database = sharedPreferences(context)
    }

    fun register(user: User){
        database.setUser(user)
    }

    fun emailAlreadyInUse(email: String) : Boolean {

        println("IS email used ?")
        println("Here it shows")
        println(database.getUserByEmail(email))
        if (database.getUserByEmail(email).isEmpty() ) {
            println("no it isn't")
            return false
        } else {
            println("yes it is")
            return true
        }

    }

    fun logout() {
        database.logOut()
    }

    fun isUserConnected() : Boolean {
        return database.isUserConnected()
    }

    fun setUser(user: User) {
        database.setUser(user)
    }
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite"
        }
    }

    fun signInUser(email : String , password : String) {
        /*val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                //progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireActivity(), "Une erreur s'est produite une", Toast.LENGTH_SHORT).show()
            }
        }*/
        //progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserEndPoint.createEndpoint().getUserByEmailAndPassword(email, password)
            withContext(Dispatchers.Main) {
                loading.value = false
                if (response.isSuccessful && response.body() != null) {
                    val user = response.body()
                    user?.let { setUser(it) }

                } else {
                    errorMessage.value = "invalid email or password"
                }
            }
        }
    }
    fun signUpUser(name: String, email: String, phone: String, password: String, picture: MultipartBody.Part?) {

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserEndPoint.createEndpoint().createUser(
                createRequestBody(name),
                createRequestBody(email),
                createRequestBody(phone),
                createRequestBody(password),
                picture
            )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    // User created successfully, handle the response
                    val newUser = response.body()
                    // Do something with the new user object

                } else {
                    // Error occurred, handle the error
                    val errorMessage = response.message()
                    // Handle the error message appropriately
                }
            }
        }
    }



    private fun createRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), value)
    }
}