package com.example.restaurants.views.main_restaurants_activity;

interface RestaurantClickListener {
    fun onFbClicked(url: String)
    fun onIgClicked(url: String)
    fun onMapClicked(latitude: String, longitude: String)
    fun onPhoneClicked(phoneNumber: String)
    fun onEmailClicked(email: String)
}
