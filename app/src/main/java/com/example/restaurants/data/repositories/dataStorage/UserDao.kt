package com.example.restaurants.data.repositories.dataStorage

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.restaurants.data.models.daoModels.User

interface UserDao {
    @Query("select * from users")
    fun getUsers(): List<User>

    // Insert a new user
    @Insert
    fun addUser(user: User)

    // Delete a user
    @Delete
    fun deleteUser(user: User)

    //update a user
    @Update
    fun updateUser(user:User)
}