package com.example.restaurants.views.main_restaurants_activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.restaurants.R
import com.example.restaurants.viewmodels.MenuViewModel
import com.example.restaurants.databinding.FragmentFoodDetailsBinding
import com.example.restaurants.viewmodels.CartViewModel

class FragmentFoodDetails : Fragment() {
    private lateinit var myModel: MenuViewModel
    private lateinit var cartModel: CartViewModel
    private lateinit var binding: FragmentFoodDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myModel = ViewModelProvider(requireActivity()).get(MenuViewModel::class.java)
        cartModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        cartModel.initViewModel(requireActivity())

        setupQuantityObserver()
        binding.plusButton.setOnClickListener {
            increaseQuantity()
        }

        binding.minusButton.setOnClickListener {
            decreaseQuantity()
        }

        binding.buttonAddToCardDetais.setOnClickListener {
            addToCart()
        }
    }

    private fun setupQuantityObserver() {
        cartModel.quantityLiveData.observe(viewLifecycleOwner) { quantity ->
            binding.counterTextDetail.text = quantity.toString()
        }
    }

    private fun increaseQuantity() {
        val currentQuantity = cartModel.quantityLiveData.value ?: 0
        val newQuantity = currentQuantity + 1
        println("heeeere"+newQuantity)
        cartModel.quantityLiveData.value = newQuantity
    }

    private fun decreaseQuantity() {
        val currentQuantity = cartModel.quantityLiveData.value ?: 0
        if (currentQuantity > 0) {
            val newQuantity = currentQuantity - 1
            cartModel.quantityLiveData.value = newQuantity
        }
    }

    private fun addToCart() {
        val itemName = myModel.item?.name
        val itemPrice = myModel.item?.price
        val itemQuantity = cartModel.quantityLiveData.value ?: 0
        if(itemQuantity!=0){
            cartModel.addMenuToCart(
                myModel.item!!,
                myModel.idRestaurant,
                itemQuantity
            )

            findNavController().navigate(R.id.action_fragmentFoodDetails_to_fragmentPannier)

        }
    }
}
