package com.example.restaurants.data.models.daoModels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val userId: String,
    val userName: String,
    val userEmail: String,
    val userPassword: String,
    val userPhone: String

)