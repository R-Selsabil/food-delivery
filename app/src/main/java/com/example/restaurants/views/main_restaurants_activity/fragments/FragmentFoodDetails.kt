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

class FragmentFoodDetails : Fragment() {
    private lateinit var myModel: MenuViewModel
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

        //val imageInt = myModel.itemDetail?.logo
        if (myModel.itemDetail != null) {
            //val imageInt = myModel.itemDetail!!.logo
            //binding.imageView2.setImageResource(imageInt)
            binding.textPriceOfItem.text = myModel.itemDetail!!.price.toString()
            binding.titleFoodDetails.text = myModel.itemDetail!!.name
            binding.textFoodDescription.text = myModel.itemDetail!!.description

            binding.plusButton.setOnClickListener {
                var int =myModel.qttOfItem.toInt()
                int++
                myModel.qttOfItem= int.toString()
                println(myModel.qttOfItem)
                binding.counterTextDetail.text = myModel.qttOfItem
            }

            binding.minusButton.setOnClickListener {
                var int =myModel.qttOfItem.toInt()
                if (int > 0) {
                    int--
                    myModel.qttOfItem = int.toString()
                    binding.counterTextDetail.text = myModel.qttOfItem
                }
            }
            binding.buttonAddToCardDetais.setOnClickListener {
                myModel.addToCart(myModel.itemDetail!!,myModel.qttOfItem.toInt())
                //val intent = Intent(requireActivity(), ActivityMainRestaurants::class.java)
                //intent.putExtra("fragment_to_show", "fragmentPannier")
                //startActivity(intent)
                findNavController().navigate(R.id.action_fragmentFoodDetails_to_fragmentPannier)
            }

        }

    }
}
