package com.example.restaurants.views.main_restaurants_activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurants.databinding.FragmentRestaurantsBinding
import com.example.restaurants.databinding.ActivityMainRestaurantsBinding
import com.example.restaurants.viewmodels.RestaurantViewModel
import com.example.restaurants.views.main_restaurants_activity.MyRestaurantClickListener
import com.example.restaurants.views.main_restaurants_activity.RestaurantClickListener
import com.example.restaurants.views.main_restaurants_activity.adapters.AdapterRestaurants

class FragmentRestaurants : Fragment() {

    lateinit var binding: FragmentRestaurantsBinding
    lateinit var restaurantModel: RestaurantViewModel
    lateinit var clickListener : RestaurantClickListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentRestaurantsBinding.inflate(layoutInflater)
        val view = binding.root
        return (view)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantModel = ViewModelProvider(requireActivity()).get(RestaurantViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        clickListener = MyRestaurantClickListener(requireActivity())
        val adapter = AdapterRestaurants(requireActivity(),clickListener)
        binding.recyclerView.adapter = adapter

        restaurantModel.loadRestaurants()


        restaurantModel.errorMessage.observe(requireActivity()) { errorMessaage ->
            Toast.makeText(requireContext(), errorMessaage, Toast.LENGTH_SHORT).show()
        }

        // List restuarants observer
        restaurantModel.restaurants.observe(requireActivity()) { restaurants ->
            adapter.setRestaurant(restaurants)
        }

    }
}