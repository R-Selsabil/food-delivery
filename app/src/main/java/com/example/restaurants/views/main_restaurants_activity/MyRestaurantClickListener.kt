package com.example.restaurants.views.main_restaurants_activity;

import android.content.Context
import com.example.restaurants.*

class MyRestaurantClickListener(private val context: Context) : RestaurantClickListener {
    override fun onFbClicked(url: String) {
        openPage(context, url, url)
    }

    override fun onIgClicked(url: String) {
        openPage(context, url, url)
    }

    override fun onMapClicked(latitude: String, longitude: String) {
        openMap(context, latitude, longitude)
    }

    override fun onPhoneClicked(phoneNumber: String) {
        openPhone(context, phoneNumber)
    }

    override fun onEmailClicked(email: String) {
        openMail(context, email)
    }

}
