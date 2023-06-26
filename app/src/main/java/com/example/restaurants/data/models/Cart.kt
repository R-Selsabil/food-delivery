package com.example.restaurants.data.models

import java.sql.Time

data class Cart(
   // var time : Time,
    var price : Int,
    var shipping : Int,
    var idUser :Int,
    var idRestaurant :Int,
    var notes :String,
    var address :String,
    var menus : List<menID>
)
