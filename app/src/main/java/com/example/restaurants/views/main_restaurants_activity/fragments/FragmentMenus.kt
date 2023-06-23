package com.example.restaurants.views.main_restaurants_activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurants.*
import com.example.restaurants.databinding.FragmentMenusBinding
import com.example.restaurants.retrofit.MenuEndPoint
import com.example.restaurants.retrofit.RestaurantEndPoint
import com.example.restaurants.views.main_restaurants_activity.adapters.AdapterMenuRestaurant
import com.example.restaurants.views.main_restaurants_activity.adapters.AdapterRestaurants
import kotlinx.coroutines.*


class FragmentMenus : Fragment() {
    lateinit var binding: FragmentMenusBinding
    var restaurantId:Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        restaurantId = arguments?.getInt("id_restaurant")!!
        super.onCreate(savedInstanceState)
        binding = FragmentMenusBinding.inflate(layoutInflater)
        val view = binding.root
        //binding.recyclerViewMenu.layoutManager = LinearLayoutManager(requireActivity())
        //binding.recyclerViewMenu.adapter = AdapterMenuRestaurant(loadData(),requireActivity())

        return (view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*binding.button.setOnClickListener {
            myModel.data.add("test")
            view.findNavController().navigate(R.id.action_fragment1_to_fragment2)
        }*/
        binding.recyclerViewMenu.layoutManager = LinearLayoutManager(requireActivity())
        loadMenus()
    }

    private fun loadMenus() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                //progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireActivity(), "Une erreur s'est produite une", Toast.LENGTH_SHORT).show()
            }
        }
        //progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = MenuEndPoint.createEndpoint().getMenuByRestaurantId(restaurantId)
            withContext(Dispatchers.Main) {
                //progressBar.visibility = View.INVISIBLE
                if (response.isSuccessful && response.body() != null) {
                    println (response.body())
                    binding.recyclerViewMenu.adapter = AdapterMenuRestaurant(response.body()!!.toMutableList(),requireActivity())
                } else {
                    Toast.makeText(requireActivity(), "Une erreur s'est produite ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}