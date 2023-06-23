package com.example.restaurants.data.models

data class Restaurant(
    val id:Int,
    val name:String,
    val logo:String,
    val type:String,
    val phone:String,
    val email : String,
    val facebook:String,
    val instagram : String,
    val address: String,
    val latitude: String,
    val longitude: String
    )