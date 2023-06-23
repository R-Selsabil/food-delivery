package com.example.restaurants.viewmodels

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.restaurants.data.models.User
import com.example.restaurants.data.repositories.sharedPreferences


class UserViewModel(context: Activity) : ViewModel() {

    private val database = sharedPreferences(context)
    fun register(user: User){
        database.setUser( user = user)
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
        var msg = database.setUser(user.email_user, user.password_user)
    }
}