package com.example.restaurants.data.repositories.dataStorage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.restaurants.data.models.daoModels.CartItem

@Database(entities = [CartItem::class],version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getCartDao(): CartDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun buildDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context,AppDatabase::class.java,
                        "Menu_db").allowMainThreadQueries().build() }

            return INSTANCE
        }
    }
}