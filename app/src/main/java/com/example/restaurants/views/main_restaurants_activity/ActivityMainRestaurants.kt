package com.example.restaurants.views.main_restaurants_activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.restaurants.R
import com.example.restaurants.databinding.ActivityMainRestaurantsBinding

class ActivityMainRestaurants : AppCompatActivity() {

    private lateinit var binding: ActivityMainRestaurantsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRestaurantsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_activity_graph) as NavHostFragment
        val navController = navHostFragment.navController
        // Add FragmentActivityRestaurants to the back stack
        //navController.graph.startDestination = R.id.fragmentActivityRestaurants
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }
}
