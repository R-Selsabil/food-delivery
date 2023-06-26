package com.example.restaurants.data.repositories

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.edit
import com.example.restaurants.data.models.User
import com.example.restaurants.utils.dataFile
import com.example.restaurants.views.authentication_activity.ActivityAuthentication


class sharedPreferences(private val context: Activity) {

    private fun transformSetToMap(): MutableMap<String, MutableMap<String, String>> {
        val pref = context.getSharedPreferences(dataFile, Context.MODE_PRIVATE)
        //pref.edit().clear().apply()
        val allKeys = pref.all.keys


        val usersKeys = allKeys.filter { it.startsWith("user_") }


        val userMap = mutableMapOf<String, MutableMap<String, String>>()
        for (userkey in usersKeys) {
            val userSetValues = pref.getStringSet(userkey, emptySet())
            val outputMap = userSetValues?.associate {
                val (key, value) = it.split(":")
                println(key)
                println(value)
                key to value
            }?.toMutableMap()
            userMap[userkey] = outputMap ?: mutableMapOf()
        }


        return userMap
    }
    fun getUserByEmail(email: String): MutableMap<String, String> {

        val users : MutableMap<String, MutableMap<String, String>> = transformSetToMap()
        val userWithEmail = mutableMapOf<String, String>()
        for ((userKey, outputMap) in users) {
            for ((key, value) in outputMap) {
                if (key == "email" && value == email) {
                    println("$key: $value")
                    userWithEmail["name"] = outputMap["name"] ?: ""
                    userWithEmail["email"] = outputMap["email"] ?: ""
                    userWithEmail["phone"] = outputMap["phone"] ?: ""
                    userWithEmail["password"] = outputMap["password"] ?: ""
                }
            }
        }


        return userWithEmail
    }

    fun getUserByEmailAndPassword(email: String,password: String): MutableMap<String, String> {
        val pref = context.getSharedPreferences(dataFile, Context.MODE_PRIVATE)
        val users : MutableMap<String, MutableMap<String, String>> = transformSetToMap()
        val userWithEmailAndPassword = mutableMapOf<String, String>()
        for ((userKey, outputMap) in users) {
            for ((key, value) in outputMap) {
                if (outputMap["email"] == email && outputMap["password"] == password ) {
                    pref.edit {
                        putBoolean("UserConnected",true) }
                    println("Value of UserConnectd")
                    println(pref.getBoolean("UserConnected",false))

                    userWithEmailAndPassword["name"] = outputMap["name"] ?: ""
                    userWithEmailAndPassword["email"] = outputMap["email"] ?: ""
                    userWithEmailAndPassword["phone"] = outputMap["phone"] ?: ""
                    userWithEmailAndPassword["password"] = outputMap["password"] ?: ""
                }
            }
        }
        Log.d("Shared","$userWithEmailAndPassword")
        return userWithEmailAndPassword
    }

    fun isUserConnected() : Boolean
    {
        val pref = context.getSharedPreferences(dataFile, Context.MODE_PRIVATE)
        return pref.getBoolean("UserConnected",false)
    }

    fun setUser(user: User)
    {
        println("on user set")
        val pref = context.getSharedPreferences(dataFile, Context.MODE_PRIVATE)
        //pref.edit().clear().apply()
        val id = "user_"+System.currentTimeMillis().toString()
        /*val userMap = mutableMapOf<String, Any>()
        userMap[id] = mutableMapOf<String, Any>().apply {
            put("name", user.name)
            put("email", user.email)
            put("password", user.password)
            put("phone", user.phone)
            put("connected", true)
        }*/
        val userMap = mutableMapOf<String, String>()
        //userMap["id"] = "user_"+System.currentTimeMillis().toString()
        userMap["name"] = user.name_user
        userMap["email"] = user.email_user
        userMap["password"] = user.password_user
        userMap["phone"] = user.phone_user
        pref.edit {
            putBoolean("UserConnected",true) }

        val editor = pref.edit()

        val userMapAsStringSet = userMap.map { entry -> "${entry.key}:${entry.value}" }.toSet()

        editor.putStringSet(id, userMapAsStringSet)

        editor.apply()
    }
    fun  setUser(email: String, password: String): String {
        var msg = ""
        // Check if email and password match static credentials
        if (email == "test@gmail.com" && password == "test") {
            // Save authentication status to shared preferences
            val pref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
            pref.edit {
                putBoolean("isAuthenticated", true)
            }
            context.finish()
        } else {
            //error message
            msg = "Invalid credentials"
        }
        return msg
    }

    fun logOut()
    {
        val pref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isAuthenticated", false)
        editor.apply()
        val intent = Intent(context, ActivityAuthentication::class.java)
        context.startActivity(intent)
    }
}