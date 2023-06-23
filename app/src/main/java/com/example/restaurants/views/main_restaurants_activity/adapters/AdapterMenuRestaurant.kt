package com.example.restaurants.views.main_restaurants_activity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.data.models.Menu
import com.example.restaurants.R
import com.example.restaurants.databinding.LayoutRestaurantMenuBinding
import com.example.restaurants.viewmodels.MenuViewModel

class AdapterMenuRestaurant(val data: List<Menu>, val ctx: FragmentActivity) :
    RecyclerView.Adapter<AdapterMenuRestaurant.MyViewHolder>() {
    //private val clickListener: MyMenuClickListener
    lateinit var myModel: MenuViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutRestaurantMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val menu = data[position]
        myModel = ViewModelProvider(ctx).get(MenuViewModel::class.java)

        holder.binding.apply {
            textMenuName.text = menu.name
            textMenuType.text = menu.category
            textMenuPrice.text = menu.price.toString()
            //imageMenu.setImageResource(menu.logo)
            //add the selected item to pannier
            buttonAddToCard.setOnClickListener {
                myModel.addToCart(menu,1)
            }
        }

        holder.itemView.setOnClickListener {
            myModel.itemDetail = Menu(1,menu.name,menu.picture,menu.price,menu.category,menu.description)
            myModel.qttOfItem="0"
            it.findNavController().navigate(R.id.action_fragmentMenus_to_fragmentFoodDetails)
        }
    }
    class MyViewHolder(val binding: LayoutRestaurantMenuBinding) : RecyclerView.ViewHolder(binding.root)
}



